package top.wzmyyj.feadapter.model

import top.wzmyyj.feadapter.R

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class MaterialLinkGoodsModel(

    var txt: String = "商品链接"

) : IFeedModelType {

    override fun getViewType(): Int {
        return R.layout.link_goods
    }
}