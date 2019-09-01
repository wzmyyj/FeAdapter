package top.wzmyyj.feadapter.model

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class MaterialPicModelWrapper(

        var item111: MaterialPic2Model? = null,
        var item112: MaterialPic3Model? = null

) : IFeedModelWrapper() {

    override fun setChildren(list: ArrayList<IFeedModelType>) {

        item111?.let {
            list.add(it)
        }
        item112?.let {
            list.add(it)
        }
    }
}