package top.wzmyyj.diff_compiler.utils;

/**
 * Created on 2020/12/01.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ProcessorConfig {

    // 需要处理的注解
    String DIFF_ANNOTATION_SAME_ITEM = "top.wzmyyj.diff_annotation.SameItem";
    String DIFF_ANNOTATION_SAME_CONTENT = "top.wzmyyj.diff_annotation.SameContent";

    // 目的是接收 包名（APT 存放的包名）
    String APT_PACKAGE = "packageNameForAPT";

    String MODEL_NAME = "Diff$$Model$$";
}
