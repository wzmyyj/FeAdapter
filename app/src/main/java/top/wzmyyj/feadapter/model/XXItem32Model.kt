package top.wzmyyj.feadapter.model

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class XXItem32Model(

    var txt: String = ""

) : IXXModelType {

    override fun getViewType(): Int {
        return IXXModelType.VIEW_TYPE_ITEM_3_2
    }
}