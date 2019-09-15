package top.wzmyyj.adapter.base

import android.util.SparseArray
import androidx.databinding.ViewDataBinding

/**
 * Created on 2019/08/31.
 * ViewTypeDelegate 的统一管理类。
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class ViewTypeDelegateManager<M : IModelType> {

    private val mIVDs: SparseArray<ViewTypeDelegate<ViewDataBinding, M>> = SparseArray()

    /**
     * 当创建 ViewHolder时。getViewType=viewType 的 ViewTypeDelegate 响应执行 onCreateVH 方法。
     * @param binding ViewDataBinding
     * @param viewType viewType
     */
    internal fun onCreateVH(binding: ViewDataBinding, viewType: Int) {
        if (mIVDs.size() == 0) return
        mIVDs.get(viewType)?.onCreateVH(binding)
    }

    /**
     * 当Bind ViewHolder时。getViewType=viewType 的 ViewTypeDelegate 响应执行 onBindVH 方法。
     * @param binding ViewDataBinding
     * @param m model
     */
    internal fun onBindVH(binding: ViewDataBinding, m: M) {
        if (mIVDs.size() == 0) return
        mIVDs.get(m.getViewType())?.onBindVH(binding, m)
    }

    /**
     * 添加ItemViewDelegate。
     *
     * @param ivd ViewTypeDelegate
     */
    @Suppress("UNCHECKED_CAST")
    fun <X : ViewDataBinding, Y : M> add(ivd: ViewTypeDelegate<X, Y>) {
        mIVDs.put(ivd.getViewType(), ivd as ViewTypeDelegate<ViewDataBinding, M>)
    }

    /**
     * 消除所有ItemViewDelegate。
     */
    fun clear() {
        mIVDs.clear()
    }
}