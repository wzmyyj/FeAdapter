package top.wzmyyj.feadapter.model

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class MaterialModelWrapper(

        var item11: MaterialPicModelWrapper? = null,
        var item12List: List<LinkMeetingModel>? = null,
        var item13: MaterialTextModel? = null

) : IFeedModelWrapper() {
    override fun setChildren(list: ArrayList<IFeedModelType>) {
        item11?.let {
            list.add(it)
        }
        item12List?.let {
            list.addAll(it)
        }
        item13?.let {
            list.add(it)
        }
    }
}