package top.wzmyyj.diff_compiler;

import com.google.auto.service.AutoService;
import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

import top.wzmyyj.diff_annotation.SameContent;
import top.wzmyyj.diff_annotation.SameItem;
import top.wzmyyj.diff_annotation.SameType;
import top.wzmyyj.diff_compiler.utils.ProcessorConfig;
import top.wzmyyj.diff_compiler.utils.ProcessorUtils;

import static top.wzmyyj.diff_compiler.utils.ProcessorConfig.DIFF_ANNOTATION_SAME_CONTENT;
import static top.wzmyyj.diff_compiler.utils.ProcessorConfig.DIFF_ANNOTATION_SAME_ITEM;
import static top.wzmyyj.diff_compiler.utils.ProcessorConfig.DIFF_ANNOTATION_SAME_TYPE;

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

public class DiffProcessor extends AbstractProcessor {

    // 操作Element的工具类（类，函数，属性，其实都是Element）
    private Elements elementTool;

    // type(类信息)的工具类，包含用于操作TypeMirror的工具方法
    private Types typeTool;

    // Message用来打印 日志相关信息
    private Messager messager;

    // 文件生成器， 类 资源 等，就是最终要生成的文件 是需要Filer来完成的
    private Filer filer;

    static class ElementGroup {
        // 集合中最近的父类类型，也是类型链表的下一个节点
        public TypeElement parent = null;
        public final Set<Element> allSet = new HashSet<>();
        public final List<Element> sameItemList = new ArrayList<>();
        public final List<Element> sameContentList = new ArrayList<>();
        // 属性Element为key, 属性类型或最近的父类类型为value
        public final Map<Element, TypeElement> sameTypeMap = new HashMap<>();
    }

    // 所有有节点根据类型缓存
    private final Map<TypeElement, ElementGroup> tempElementMap = new HashMap<>();
    // 所有以叶子子类为起点，最近的父类为下一个节点 的单链表集合
    private final Set<ElementGroup> tempLeafTypeSet = new HashSet<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        elementTool = processingEnvironment.getElementUtils();
        messager = processingEnvironment.getMessager();
        filer = processingEnvironment.getFiler();
        typeTool = processingEnvironment.getTypeUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (ProcessorUtils.isNullOrEmpty(set)) {
            messager.printMessage(Diagnostic.Kind.NOTE, "没有发现 被@SameItem, @SameContent, @SameType 注解的地方呀！");
            return false; // 没有机会处理
        }
        // 获取所有被 @SameItem, @SameContent, @SameType 注解的 元素集合
        Set<? extends Element> elements1 = roundEnvironment.getElementsAnnotatedWith(SameItem.class);
        Set<? extends Element> elements2 = roundEnvironment.getElementsAnnotatedWith(SameContent.class);
        Set<? extends Element> elements3 = roundEnvironment.getElementsAnnotatedWith(SameType.class);
        // 缓存数据
        saveElementSameItem(elements1);
        saveElementSameContent(elements2);
        saveElementSameType(elements3);
        // 得到所有需要处理的类型
        if (ProcessorUtils.isNullOrEmpty(tempElementMap)) return false;
        // 整理类型的继承关系
        arrangeSuperType();
        // 修正 @SameType 注解的属性类型
        fixSameTypeFieldType();

        // 创建文件
        createFile();

        return true;
    }

    /**
     * 缓存 被 {@link SameItem} 注解的元素。
     *
     * @param elements 被 {@link SameItem} 注解的集合。
     */
    private void saveElementSameItem(Set<? extends Element> elements) {
        if (!ProcessorUtils.isNullOrEmpty(elements)) {
            for (Element element : elements) {
                // 注解在属性的上面，属性节点父节点 是 类节点
                TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
                if (tempElementMap.containsKey(enclosingElement)) {
                    ElementGroup group = tempElementMap.get(enclosingElement);
                    if (group.allSet.contains(element)) {
                        throw new RuntimeException("@SameItem, @SameContent, @SameType 不能同时作用在一个属性上！");
                    }
                    group.allSet.add(element);
                    group.sameItemList.add(element);
                } else { // 没有key (类型）
                    ElementGroup group = new ElementGroup();
                    group.allSet.add(element);
                    group.sameItemList.add(element);
                    tempElementMap.put(enclosingElement, group); // 加入缓存
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
        if (!ProcessorUtils.isNullOrEmpty(elements)) {
            for (Element element : elements) {
                // 注解在属性的上面，属性节点父节点 是 类节点
                TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
                if (tempElementMap.containsKey(enclosingElement)) {
                    ElementGroup group = tempElementMap.get(enclosingElement);
                    if (group.allSet.contains(element)) {
                        throw new RuntimeException("@SameItem, @SameContent, @SameType 不能同时作用在一个属性上！");
                    }
                    group.allSet.add(element);
                    group.sameContentList.add(element);
                } else { // 没有key (类型）
                    ElementGroup group = new ElementGroup();
                    group.allSet.add(element);
                    group.sameContentList.add(element);
                    tempElementMap.put(enclosingElement, group); // 加入缓存
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
        if (!ProcessorUtils.isNullOrEmpty(elements)) {
            for (Element element : elements) {
                if (element.asType().getKind() != TypeKind.DECLARED) {
                    throw new RuntimeException("@SameType 只能作用在声明类型的属性上！");
                }
                // 注解在属性的上面，属性节点父节点 是 类节点
                TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
                if (tempElementMap.containsKey(enclosingElement)) {
                    ElementGroup group = tempElementMap.get(enclosingElement);
                    if (group.allSet.contains(element)) {
                        throw new RuntimeException("@SameItem, @SameContent, @SameType 不能同时作用在一个属性上！");
                    }
                    group.allSet.add(element);
                    // 类型先为空
                    group.sameTypeMap.put(element, null);
                } else { // 没有key (类型）
                    ElementGroup group = new ElementGroup();
                    group.allSet.add(element);
                    // 类型先为空
                    group.sameTypeMap.put(element, null);
                    tempElementMap.put(enclosingElement, group); // 加入缓存
                }
            }
        }
    }

    /**
     * 整理类型继承关系。
     */
    private void arrangeSuperType() {
        tempLeafTypeSet.addAll(tempElementMap.values());
        Set<TypeElement> typeSet = tempElementMap.keySet();
        for (TypeElement te : typeSet) {
            TypeElement p = te;
            while (p != null) {
                p = getSuperclass(p);
                if (p != null && typeSet.contains(p)) {
                    tempElementMap.get(te).parent = p;
                    tempLeafTypeSet.remove(tempElementMap.get(p));
                    break;
                }
            }
        }
    }

    /**
     * 修正 被 {@link SameType} 注解的属性类型
     */
    private void fixSameTypeFieldType() {
        Set<TypeElement> typeSet = tempElementMap.keySet();
        for (TypeElement et : typeSet) {
            Map<Element, TypeElement> map = tempElementMap.get(et).sameTypeMap;
            if (map.isEmpty()) continue;
            for (Map.Entry<Element, TypeElement> entry : map.entrySet()) {
                //todo
                TypeMirror typeMirror = entry.getKey().asType();

            }
        }
    }

    private TypeElement getCloseType(Element type) {
        return null;
    }

    /**
     * 创建文件。
     */
    private void createFile() {

    }

    private TypeElement getSuperclass(TypeElement type) {
        if (type.getSuperclass().getKind() == TypeKind.DECLARED) {
            TypeElement superclass = (TypeElement) processingEnv.getTypeUtils().asElement(type.getSuperclass());
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


}
