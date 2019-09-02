package top.wzmyyj.feadapter.model

import top.wzmyyj.feadapter.R

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class MaterialTextModel(

    var txt: String = "文本内容: 哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"

) : IFeedModelType {

    override fun getViewType(): Int {
        return R.layout.material_text
    }
}