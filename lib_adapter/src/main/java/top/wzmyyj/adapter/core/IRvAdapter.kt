package top.wzmyyj.adapter.core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created on 2020/10/21.
 *
 * Please let your RecyclerView.Adapter implements IExtAdapter.
 *
 * @author feling
 * @version 1.0.1
 * @since 1.0.0
 */
@MainThread
interface IRvAdapter<M : IVhModelType> {

    /**
     * What to do when creating the viewHolder for all.
     */
    fun onCreateVHForAll(binding: ViewDataBinding)

    /**
     * What to do when binding the viewHolder for all.
     */
    fun onBindVHForAll(binding: ViewDataBinding, m: M)

    /**
     * Create BaseViewHolder.
     */
    fun createVH(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), viewType, parent, false
        )
        return BindingViewHolder(binding)
    }

    /**
     * hat to do after binding the viewHolder for all.
     */
    fun afterBindVH(binding: ViewDataBinding, m: M) {}

    /**
     * Init ViewTypeDelegateManager. You can add VTDs.
     */
    fun initManager(manager: ViewTypeDelegateManager<M>) {}

    /**
     * Get item by position.
     */
    fun getModel(position: Int): M?

}