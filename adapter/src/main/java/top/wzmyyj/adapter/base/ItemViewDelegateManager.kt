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
     * 当创建 ViewHolder时。getViewType=viewType 的 ItemViewDelegate 响应执行 onCreateVH 方法。
     * @param binding ViewDataBinding
     * @param viewType viewType
     */
    internal fun onCreateVH(binding: ViewDataBinding, viewType: Int) {
        if (ivdList.isEmpty()) return
        ivdList
                .filter { ivd ->
                    ivd.getViewType() == viewType
                }
                .forEach { ivd ->
                    ivd.onCreateVH(binding)
                }
    }

    /**
     * 当Bind ViewHolder时。getViewType=viewType 的 ItemViewDelegate 响应执行 onBindVH 方法。
     * @param binding ViewDataBinding
     * @param m model
     */
    internal fun onBindVH(binding: ViewDataBinding, m: M) {
        if (ivdList.isEmpty()) return
        ivdList
                .filter { ivd ->
                    ivd.getViewType() == m.getViewType()
                }.forEach { ivd ->
                    ivd.onBindVH(binding, m)
                }
    }

    /**
     * 添加ItemViewDelegate。
     *
     * @param ivd ItemViewDelegate
     */
    @Suppress("UNCHECKED_CAST")
    fun <X : ViewDataBinding, Y : M> add(ivd: ItemViewDelegate<X, Y>) {
        ivdList.add(ivd as ItemViewDelegate<ViewDataBinding, M>)
    }

    /**
     * 消除所有ItemViewDelegate。
     */
    fun clear() {
        ivdList.clear()
    }
}