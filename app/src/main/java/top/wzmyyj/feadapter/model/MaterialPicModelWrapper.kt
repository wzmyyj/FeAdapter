package top.wzmyyj.feadapter.model

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class MaterialPicModelWrapper(

        var pic2List: List<MaterialPic2Model>? = null,
        var pic3List: List<MaterialPic3Model>? = null

) : IFeedModelWrapper() {

    override fun setChildren(list: ArrayList<IFeedModelType>) {

        pic2List?.let {
            list.addAll(it)
        }
        pic3List?.let {
            list.addAll(it)
        }
    }
}