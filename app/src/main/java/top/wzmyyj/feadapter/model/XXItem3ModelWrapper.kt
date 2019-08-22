package top.wzmyyj.feadapter.model

import top.wzmyyj.feadapter.base.IModelTree

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
    override fun addChild(list: MutableList<IXXModelType>) {
        item31?.let {
            list.add(it)
        }
        item32?.let {
            list.add(it)
        }
    }
}