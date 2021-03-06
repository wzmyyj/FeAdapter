package top.wzmyyj.adapter.core

import android.util.SparseArray
import androidx.databinding.ViewDataBinding

/**
 * Created on 2020/10/21.
 *
 * The unified management class of ViewTypeDelegate.
 *
 * @author feling
 * @version 1.0.1
 * @since 1.0.0
 * @see ViewTypeDelegate
 */
class ViewTypeDelegateManager<M : IVhModelType> {

    private val mIVDs: SparseArray<ViewTypeDelegate<ViewDataBinding, M>> = SparseArray()
    private val mIVD2s: SparseArray<ViewTypeDelegate2<ViewDataBinding, M>> = SparseArray()

    /**
     * When creating viewHolder. if VTD.getViewType() == viewType executes VTD.onCreateVH().
     *
     * @param binding ViewDataBinding
     * @param viewType viewType
     */
    internal fun onCreateVH(binding: ViewDataBinding, viewType: Int) {
        if (mIVDs.size() == 0) return
        mIVDs.get(viewType)?.onCreateVH(binding)
    }

    /**
     * When bind viewHolder. if VTD.getViewType() == model.viewType executes VTD.onBindVh().
     *
     * @param binding ViewDataBinding
     * @param m model
     */
    internal fun onBindVH(binding: ViewDataBinding, m: M) {
        if (mIVDs.size() == 0) return
        mIVDs.get(m.getViewType())?.onBindVH(binding, m)
    }

    /**
     * When bind viewHolder. if VTD.getViewType() == model.viewType executes VTD.onBindVh().
     *
     * @param binding ViewDataBinding
     * @param m model
     * @param payload payload
     * @return handle onBindViewHolder
     */
    internal fun onBindVH(binding: ViewDataBinding, m: M, payload: Any): Boolean {
        if (mIVD2s.size() == 0) return false
        return mIVD2s.get(m.getViewType())?.onBindVH(binding, m, payload) ?: false
    }

    /**
     * Add VTD into manager.
     *
     * @param ivd VTD
     */
    @Suppress("UNCHECKED_CAST")
    fun <X : ViewDataBinding, Y : M> add(ivd: ViewTypeDelegate<X, Y>) {
        mIVDs.put(ivd.getViewType(), ivd as ViewTypeDelegate<ViewDataBinding, M>)
        if (ivd is ViewTypeDelegate2) {
            mIVD2s.put(ivd.getViewType(), ivd as ViewTypeDelegate2<ViewDataBinding, M>)
        }
    }

    /**
     * Eliminate all item's VTD.
     */
    fun clear() {
        mIVDs.clear()
    }

}
