package top.wzmyyj.feadapter.model

import java.lang.RuntimeException

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
abstract class IXXModelWrapper : IXXModelType {

    final override fun getViewType(): Int {
        throw RuntimeException("IXXModelWrapper can not getViewType()!")
    }

    final override fun getChildren(): List<IXXModelType>? {
        return ArrayList<IXXModelType>().also {
            addChild(it)
        }
    }

    protected abstract fun addChild(list: MutableList<IXXModelType>)

}