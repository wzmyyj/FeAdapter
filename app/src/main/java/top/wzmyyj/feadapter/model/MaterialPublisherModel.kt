package top.wzmyyj.feadapter.model

import top.wzmyyj.feadapter.R

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class MaterialPublisherModel(

    var txt: String = "发布者，素材头部"

) : IFeedModelType {

    override fun getViewType(): Int {
        return R.layout.material_publisher
    }
}