package top.wzmyyj.adapter.core

import androidx.databinding.ViewDataBinding

/**
 * Created on 2020/12/09.
 *
 * @author feling
 * @version 1.0.1
 * @since 1.0.1
 */
interface ViewTypeDelegate2<DB : ViewDataBinding, M : IVhModelType> : ViewTypeDelegate<DB, M> {

    override fun onCreateVH(binding: DB) {}

    override fun onBindVH(binding: DB, m: M) {}

    /**
     * What to do when binding the viewHolder.
     *
     * @param binding ViewDataBinding
     * @param m model
     */
    fun onBindVH(binding: DB, m: M, payload: Any): Boolean
}