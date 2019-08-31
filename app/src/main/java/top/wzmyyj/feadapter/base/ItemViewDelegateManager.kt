package top.wzmyyj.feadapter.base

import androidx.databinding.ViewDataBinding

/**
 * Created on 2019/08/31.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class ItemViewDelegateManager<M : IModelType> {

    private val ivdList: MutableList<ItemViewDelegate<ViewDataBinding, M>> = ArrayList()


    fun onCreateVH(viewType: Int, binding: ViewDataBinding) {
        ivdList.forEach { ivd ->
            if (ivd.isForViewType(viewType)) {
                ivd.onCreateVH(binding)
            }
        }
    }

    fun onBindVH(viewType: Int, binding: ViewDataBinding, m: M) {
        ivdList.forEach { ivd ->
            if (ivd.isForViewType(viewType)) {
                ivd.onBindVH(binding, m)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <X : ViewDataBinding, Y : M> addIvd(ivd: ItemViewDelegate<X, Y>) {
        ivdList.add(ivd as ItemViewDelegate<ViewDataBinding, M>)
    }

    fun clear() {
        ivdList.clear()
    }
}