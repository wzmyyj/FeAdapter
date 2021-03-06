package top.wzmyyj.adapter.core

import androidx.databinding.ViewDataBinding

/**
 * Created on 2020/10/21.
 *
 * A class model(same viewType) corresponds to a delegate object.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
interface ViewTypeDelegate<DB : ViewDataBinding, M : IVhModelType> {

    /**
     * The delegate takes effect when this.getViewType() == model.getViewType().
     *
     * @return viewType
     */
    fun getViewType(): Int

    /**
     * What to do when creating the viewHolder.
     *
     * @param binding ViewDataBinding
     */
    fun onCreateVH(binding: DB)

    /**
     * What to do when binding the viewHolder.
     *
     * @param binding ViewDataBinding
     * @param m model
     */
    fun onBindVH(binding: DB, m: M)

}