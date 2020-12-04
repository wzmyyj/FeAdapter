package top.wzmyyj.diff_compiler;

import com.google.auto.service.AutoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import top.wzmyyj.diff_annotation.SameContent;
import top.wzmyyj.diff_annotation.SameItem;
import top.wzmyyj.diff_annotation.SameType;
import top.wzmyyj.diff_compiler.utils.EmptyUtils;

import static javax.tools.Diagnostic.Kind.ERROR;
import static javax.tools.Diagnostic.Kind.NOTE;
import static top.wzmyyj.diff_compiler.utils.ProcessorConfig.DIFF_ANNOTATION_SAME_CONTENT;
import static top.wzmyyj.diff_compiler.utils.ProcessorConfig.DIFF_ANNOTATION_SAME_ITEM;
import static top.wzmyyj.diff_compiler.utils.ProcessorConfig.DIFF_ANNOTATION_SAME_TYPE;
import static top.wzmyyj.diff_compiler.utils.ProcessorConfig.OPTIONS_MODULE_NAME;
import static top.wzmyyj.diff_compiler.utils.ProcessorConfig.OPTIONS_PACKAGE_NAME;

/**
 * Created on 2020/12/01.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
// AutoService则是固定的写法，加个注解即可
// 通过auto-service中的@AutoService可以自动生成AutoService注解处理器，用来注册
// 用来生成 META-INF/services/javax.annotation.processing.Processor 文件
@AutoService(Processor.class)

// 指定JDK编译版本
@SupportedSourceVersion(SourceVersion.RELEASE_7)

// 允许/支持的注解类型，让注解处理器处理
@SupportedAnnotationTypes({DIFF_ANNOTATION_SAME_ITEM, DIFF_ANNOTATION_SAME_CONTENT, DIFF_ANNOTATION_SAME_TYPE})

// 注解处理器接收的参数
@SupportedOptions({OPTIONS_MODULE_NAME, OPTIONS_PACKAGE_NAME})

public class DiffProcessor extends AbstractProcessor {

    // 操作Element的工具类（类，函数，属性，其实都是Element）
    private Elements elementTool;

    // type(类信息)的工具类，包含用于操作TypeMirror的工具方法
    private Types typeTool;

    // Message用来打印 日志相关信息
    private Messager messager;

    // 文件生成器， 类 资源 等，就是最终要生成的文件 是需要Filer来完成的
    private Filer filer;

    // 各个模块传递过来的模块名
    private String moduleName;
    // 各个模块传递过来的包名
    private String packageName;

    // 单链表缓存数据和处理继承关系
    static class ElementNode {
        // 当前类型
        public TypeElement data = null;
        // 集合中最近的父类类型，下一个节点
        public ElementNode next = null;
        public final Set<VariableElement> allSet = new HashSet<>();
        public final List<VariableElement> sameItemList = new ArrayList<>();
        public final List<VariableElement> sameContentList = new ArrayList<>();
        // 穿透属性map，Element为key, 属性类型或最近的父类类型为value
        public final Map<VariableElement, ElementNode> sameTypeMap = new HashMap<>();
    }

    // 所有有节点根据类型缓存
    private final Map<TypeElement, ElementNode> tempElementMap = new HashMap<>();
    // 所有以叶子子类为起点，最近的父类为下一个节点 的单链表集合
    private final Set<ElementNode> rootNodeSet = new HashSet<>();
    // 记录生成过的model文件
    private final Map<ElementNode, Object> tempModelMap = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        elementTool = processingEnvironment.getElementUtils();
        messager = processingEnvironment.getMessager();
        filer = processingEnvironment.getFiler();
        typeTool = processingEnvironment.getTypeUtils();
        moduleName = processingEnvironment.getOptions().get(OPTIONS_MODULE_NAME);
        packageName = processingEnvironment.getOptions().get(OPTIONS_PACKAGE_NAME);
        note(">>>>>>>>>>>>>>>>>>>>>>" + OPTIONS_MODULE_NAME + "：" + moduleName);
        note(">>>>>>>>>>>>>>>>>>>>>>" + OPTIONS_PACKAGE_NAME + "：" + packageName);
        if (moduleName != null && packageName != null) {
            note("APT 环境搭建完成....");
        } else {
            note("APT 环境有问题，请检查 " + OPTIONS_MODULE_NAME + " 与 " + OPTIONS_PACKAGE_NAME + " 为null...");
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (EmptyUtils.isNullOrEmpty(set)) return false;
        // 获取所有被 @SameItem, @SameContent, @SameType 注解的 元素集合
        Set<? extends Element> elements1 = roundEnvironment.getElementsAnnotatedWith(SameItem.class);
        Set<? extends Element> elements2 = roundEnvironment.getElementsAnnotatedWith(SameContent.class);
        Set<? extends Element> elements3 = roundEnvironment.getElementsAnnotatedWith(SameType.class);

        note("@SameItem 数量：" + elements1.size());
        note("@SameContent 数量：" + elements2.size());
        note("@SameType 数量：" + elements3.size());
        // 缓存数据
        saveElementSameItem(elements1);
        saveElementSameContent(elements2);
        saveElementSameType(elements3);
        // 已得到所有需要处理的类型
        if (EmptyUtils.isNullOrEmpty(tempElementMap)) return false;
        // 整理类型的继承关系
        arrangeSuperType();
        // 修正 @SameType 注解的属性类型
        fixSameTypeFieldType();

        // 创建文件
        createAllModelFile();

        return true;
    }

    //--------------搜集数据----------------//

    /**
     * 缓存 被 {@link SameItem} 注解的元素。
     *
     * @param elements 被 {@link SameItem} 注解的集合。
     */
    private void saveElementSameItem(Set<? extends Element> elements) {
        if (!EmptyUtils.isNullOrEmpty(elements)) {
            for (Element element : elements) {
                VariableElement ve = (VariableElement) element;
                // 注解在属性的上面，属性节点父节点 是 类节点
                TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
                if (tempElementMap.containsKey(enclosingElement)) {
                    ElementNode node = tempElementMap.get(enclosingElement);
                    if (node.allSet.contains(ve)) {
                        error("@SameItem, @SameContent, @SameType 不能同时作用在一个属性上！");
                    }
                    node.allSet.add(ve);
                    node.sameItemList.add(ve);
                } else { // 没有key (类型）
                    ElementNode node = new ElementNode();
                    node.data = enclosingElement;
                    node.allSet.add(ve);
                    node.sameItemList.add(ve);
                    tempElementMap.put(enclosingElement, node); // 加入缓存
                }
            }
        }
    }

    /**
     * 缓存 被 {@link SameContent} 注解的元素。
     *
     * @param elements 被 {@link SameContent} 注解的集合。
     */
    private void saveElementSameContent(Set<? extends Element> elements) {
        if (!EmptyUtils.isNullOrEmpty(elements)) {
            for (Element element : elements) {
                VariableElement ve = (VariableElement) element;
                // 注解在属性的上面，属性节点父节点 是 类节点
                TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
                if (tempElementMap.containsKey(enclosingElement)) {
                    ElementNode node = tempElementMap.get(enclosingElement);
                    if (node.allSet.contains(ve)) {
                        error("@SameItem, @SameContent, @SameType 不能同时作用在一个属性上！");
                    }
                    node.allSet.add(ve);
                    node.sameContentList.add(ve);
                } else { // 没有key (类型）
                    ElementNode node = new ElementNode();
                    node.data = enclosingElement;
                    node.allSet.add(ve);
                    node.sameContentList.add(ve);
                    tempElementMap.put(enclosingElement, node); // 加入缓存
                }
            }
        }
    }

    /**
     * 缓存 被 {@link SameType} 注解的元素。
     *
     * @param elements 被 {@link SameType} 注解的集合。
     */
    private void saveElementSameType(Set<? extends Element> elements) {
        if (!EmptyUtils.isNullOrEmpty(elements)) {
            Set<TypeElement> typeSet = new HashSet<>(tempElementMap.keySet());
            for (Element element : elements) {
                VariableElement ve = (VariableElement) element;
                if (ve.asType().getKind() != TypeKind.DECLARED) {
                    error("@SameType 只能作用在声明类型的属性上！");
                }
                // 从已有的类型里查找有无属性的类型或父类
                TypeMirror typeMirror = ve.asType();
                boolean hasType = false;
                for (TypeElement te : typeSet) {
                    TypeMirror tm = te.asType();
                    if (typeTool.isSameType(typeMirror, tm) || typeTool.isSubtype(typeMirror, te.asType())) {
                        hasType = true;
                        break;
                    }
                }
                if (!hasType) {
                    error("@SameType 的属性类型 或其父类, 没有被 @SameItem 或 @SameContent 注解！");
                }
                // 注解在属性的上面，属性节点父节点 是 类节点
                TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
                if (tempElementMap.containsKey(enclosingElement)) {
                    ElementNode group = tempElementMap.get(enclosingElement);
                    if (group.allSet.contains(ve)) {
                        error("@SameItem, @SameContent, @SameType 不能同时作用在一个属性上！");
                    }
                    group.allSet.add(ve);
                    // 类型先为空
                    group.sameTypeMap.put(ve, null);
                } else { // 没有key (类型）
                    ElementNode group = new ElementNode();
                    group.data = enclosingElement;
                    group.allSet.add(ve);
                    // 类型先为空
                    group.sameTypeMap.put(ve, null);
                    tempElementMap.put(enclosingElement, group); // 加入缓存
                }
            }
        }
    }

    //--------------整理数据----------------//

    /**
     * 整理类型继承关系，组成多个单链表。
     */
    private void arrangeSuperType() {
        rootNodeSet.addAll(tempElementMap.values());
        Set<TypeElement> typeSet = tempElementMap.keySet();
        for (TypeElement te : typeSet) {
            TypeElement p = te;
            while (p != null) {
                p = getSuperclass(p);
                if (p != null && typeSet.contains(p)) {
                    ElementNode node = tempElementMap.get(p);
                    tempElementMap.get(te).next = node;
                    rootNodeSet.remove(node);
                    break;
                }
            }
        }
    }

    /**
     * 修正 被 {@link SameType} 注解的属性类型，找到最近的类型。
     */
    private void fixSameTypeFieldType() {
        Set<TypeElement> typeSet = tempElementMap.keySet();
        for (TypeElement et : typeSet) {
            Map<VariableElement, ElementNode> map = tempElementMap.get(et).sameTypeMap;
            if (map.isEmpty()) continue;
            for (VariableElement element : map.keySet()) {
                TypeMirror typeMirror = element.asType();
                for (ElementNode node : rootNodeSet) {
                    boolean isFind = false;
                    ElementNode next = node;
                    while (next != null) {
                        TypeMirror tm = next.data.asType();
                        if (typeTool.isSameType(typeMirror, tm) || typeTool.isSubtype(typeMirror, tm)) {
                            map.put(element, next);
                            isFind = true;
                            break;
                        }
                        next = next.next;
                    }
                    if (isFind) break;
                }
                if (map.get(element) == null) {// 正常情况下不会，仅仅做个校验
                    error("一定是哪里错了！");
                }
            }
        }
    }

    /**
     * 查找父类（只找用户定义的类）。如果父类 是SDK的类，返回null。
     *
     * @param type TypeElement
     * @return 父类的 TypeElement
     */
    private TypeElement getSuperclass(TypeElement type) {
        if (type.getSuperclass().getKind() == TypeKind.DECLARED) {
            TypeElement superclass = (TypeElement) typeTool.asElement(type.getSuperclass());
            String name = superclass.getQualifiedName().toString();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                // Skip system classes, this just degrades performance
                return null;
            } else {
                return superclass;
            }
        } else {
            return null;
        }
    }

    //--------------创建文件----------------//

    /**
     * 创建所有model文件。
     */
    private void createAllModelFile() {
        createModelNodeSet.clear();
        // 从每个叶子子类开始
        for (ElementNode node : rootNodeSet) {
            createModelFile(node);
        }
    }

    // 记录是否进入过createModelFile方法，防止造成无限递归。
    private final Set<ElementNode> createModelNodeSet = new HashSet<>();

    /**
     * 生成单个model的文件。存在递归调用。
     * 如果 穿透属性 有闭环，可能造成无限递归，因此会检测出来并抛异常。
     *
     * @param node 信息
     */
    private void createModelFile(ElementNode node) {
        if (node == null) return;
        if (tempModelMap.get(node) != null) return;// 生成过了
        if (createModelNodeSet.contains(node)) {
            error("小老弟 写的 @SameType 的属性 存在闭环呀！");
        }
        createModelNodeSet.add(node);
        // 优先生成父类文件
        if (node.next != null) {
            createModelFile(node.next);
        }
        // 优先生成穿透属性类型文件
        if (!node.sameTypeMap.isEmpty()) {
            for (ElementNode n : node.sameTypeMap.values()) {
                createModelFile(n);
            }
        }
        tempModelMap.put(node, new Object());

//        for (VariableElement element : node.allSet) {
//            ClassName cn=
//            Type type = element.asType().getKind().;
//            FieldSpec.Builder builder = FieldSpec.builder(type, element.getSimpleName(), Modifier.PRIVATE);
//
//        }

    }


    private void note(String msg) {
        messager.printMessage(NOTE, msg + "; ");
    }

    private void error(String msg) {
        messager.printMessage(ERROR, msg + "; ");
        throw new RuntimeException("报错了哦！");
    }

}
