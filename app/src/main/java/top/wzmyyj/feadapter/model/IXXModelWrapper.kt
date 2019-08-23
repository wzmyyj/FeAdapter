package top.wzmyyj.feadapter.model

/**
 * Created on 2019/08/23.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
abstract class IXXModelWrapper : IXXModelType {
    final override fun getViewType(): Int {
        throw RuntimeException("$this can not getViewType()")
    }

    final override fun getChildren(): List<IXXModelType>? {
        return ArrayList<IXXModelType>().also { setChildren(it) }
    }

    protected abstract fun setChildren(list: ArrayList<IXXModelType>)
}