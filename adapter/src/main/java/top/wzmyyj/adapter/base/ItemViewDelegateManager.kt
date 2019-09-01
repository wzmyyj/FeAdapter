package top.wzmyyj.adapter.base

import androidx.databinding.ViewDataBinding

/**
 * Created on 2019/08/31.
 * 所有Item对View的处理委托统一管理类。
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class ItemViewDelegateManager<M : IModelType> {

    private val ivdList: MutableList<ItemViewDelegate<ViewDataBinding, M>> = ArrayList()

    /**
     * 当创建 ViewHolder时。isForViewType=true 的 ItemViewDelegate 响应执行 onCreateVH 方法。
     * @param viewType viewType
     * @param binding ViewDataBinding
     */
    internal fun onCreateVH(viewType: Int, binding: ViewDataBinding) {
        ivdList.forEach { ivd ->
            if (ivd.isForViewType(viewType)) {
                ivd.onCreateVH(binding)
            }
        }
    }

    /**
     * 当Bind ViewHolder时。isForViewType=true的 ItemViewDelegate 响应执行 onBindVH 方法。
     * @param viewType viewType
     * @param binding ViewDataBinding
     * @param m model
     */
    internal fun onBindVH(viewType: Int, binding: ViewDataBinding, m: M) {
        ivdList.forEach { ivd ->
            if (ivd.isForViewType(viewType)) {
                ivd.onBindVH(binding, m)
            }
        }
    }

    /**
     * 添加ItemViewDelegate。
     *
     * @param ivd ItemViewDelegate
     */
    @Suppress("UNCHECKED_CAST")
    fun <X : ViewDataBinding, Y : M> addIvd(ivd: ItemViewDelegate<X, Y>) {
        ivdList.add(ivd as ItemViewDelegate<ViewDataBinding, M>)
    }

    /**
     * 消除所有ItemViewDelegate。
     */
    fun clear() {
        ivdList.clear()
    }
}