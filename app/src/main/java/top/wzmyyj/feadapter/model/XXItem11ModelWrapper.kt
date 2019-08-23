package top.wzmyyj.feadapter.model

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class XXItem11ModelWrapper(

        var item111: XXItem111Model? = null,
        var item112: XXItem112Model? = null

) : IXXModelWrapper() {

    override fun setChildren(list: ArrayList<IXXModelType>) {

        item111?.let {
            list.add(it)
        }
        item112?.let {
            list.add(it)
        }
    }
}