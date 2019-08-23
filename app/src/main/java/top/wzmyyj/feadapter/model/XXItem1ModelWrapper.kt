package top.wzmyyj.feadapter.model

import top.wzmyyj.feadapter.base.IModelTree

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class XXItem1ModelWrapper(

        var item11: XXItem11ModelWrapper? = null,
        var item12List: List<XXItem12Model>? = null,
        var item13: XXItem13Model? = null

) : IXXModelType {
    override fun getChildren(): List<IXXModelType>? {
        return ArrayList<IXXModelType>().also { list ->
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

}