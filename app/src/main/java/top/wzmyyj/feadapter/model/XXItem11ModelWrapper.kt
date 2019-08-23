package top.wzmyyj.feadapter.model

import top.wzmyyj.feadapter.base.IModelTree

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

) : IXXModelType {

    override fun getChildren(): List<IXXModelType>? {
        return ArrayList<IXXModelType>().also { list ->
            item111?.let {
                list.add(it)
            }
            item112?.let {
                list.add(it)
            }
        }
    }
}