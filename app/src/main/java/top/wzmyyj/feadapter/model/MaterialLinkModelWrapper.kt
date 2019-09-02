package top.wzmyyj.feadapter.model

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class MaterialLinkModelWrapper(

        var linkGoods: MaterialLinkGoodsModel? = null,
        var linkMeeting: MaterialLinkMeetingModel? = null

) : IFeedModelWrapper() {
    override fun setChildren(list: ArrayList<IFeedModelType>) {
        linkGoods?.let {
            list.add(it)
        }
        linkMeeting?.let {
            list.add(it)
        }
    }
}