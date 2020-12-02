package top.wzmyyj.diff_compiler;

import com.google.auto.service.AutoService;

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
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

import top.wzmyyj.diff_annotation.SameContent;
import top.wzmyyj.diff_annotation.SameItem;
import top.wzmyyj.diff_compiler.utils.ProcessorConfig;

import static top.wzmyyj.diff_compiler.utils.ProcessorConfig.DIFF_ANNOTATION_SAME_CONTENT;
import static top.wzmyyj.diff_compiler.utils.ProcessorConfig.DIFF_ANNOTATION_SAME_ITEM;

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
@SupportedAnnotationTypes({DIFF_ANNOTATION_SAME_ITEM, DIFF_ANNOTATION_SAME_CONTENT})

public class DiffProcessor extends AbstractProcessor {

    // 操作Element的工具类（类，函数，属性，其实都是Element）
    private Elements elementTool;

    // type(类信息)的工具类，包含用于操作TypeMirror的工具方法
    private Types typeTool;

    // Message用来打印 日志相关信息
    private Messager messager;

    // 文件生成器， 类 资源 等，就是最终要生成的文件 是需要Filer来完成的
    private Filer filer;

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
        if (set.isEmpty()) {
            messager.printMessage(Diagnostic.Kind.NOTE, "没有发现 被@SameItem 或 @SameContent 注解的地方呀！");
            return false; // 没有机会处理
        }

        TypeElement modelType = elementTool.getTypeElement(ProcessorConfig.TYPE_MODEL_TYPE);

        // 获取所有被 @SameItem, @SameContent 注解的 元素集合
        Set<? extends Element> elements1 = roundEnvironment.getElementsAnnotatedWith(SameItem.class);
        Set<? extends Element> elements2 = roundEnvironment.getElementsAnnotatedWith(SameContent.class);




        return false;
    }
}
