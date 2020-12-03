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
    String DIFF_ANNOTATION_SAME_TYPE = "top.wzmyyj.diff_annotation.SameType";

    String DIFF_API_PACKAGE = " top.wzmyyj.diff_api";

    String MODEL_NAME = "Diff$$Model$$";

    String TYPE_MODEL_TYPE = DIFF_API_PACKAGE + ".IDiffModelType";
    String TYPE_PAYLOAD = DIFF_API_PACKAGE + ".Payload";
}
