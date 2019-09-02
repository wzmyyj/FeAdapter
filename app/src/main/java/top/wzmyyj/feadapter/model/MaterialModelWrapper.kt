package top.wzmyyj.feadapter.model

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class MaterialModelWrapper(

        var publisher: MaterialPublisherModel? = null,
        var text: MaterialTextModel? = null,
        var pic: MaterialPicModelWrapper? = null,
        var link: MaterialLinkModelWrapper? = null,
        var bottom: MaterialBottomModel? = null

) : IFeedModelWrapper() {
    override fun setChildren(list: ArrayList<IFeedModelType>) {
        publisher?.let {
            list.add(it)
        }
        text?.let {
            list.add(it)
        }
        pic?.let {
            list.add(it)
        }
        link?.let {
            list.add(it)
        }
        bottom?.let {
            list.add(it)
        }
    }
}