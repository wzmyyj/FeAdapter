package top.wzmyyj.feadapter.model

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class XXItem3ModelWrapper(

        var item31: XXItem31Model? = null,
        var item32: XXItem32Model? = null

) : IXXModelWrapper() {

    override fun setChildren(list: ArrayList<IXXModelType>) {
        item31?.let {
            list.add(it)
        }
        item32?.let {
            list.add(it)
        }
    }
}