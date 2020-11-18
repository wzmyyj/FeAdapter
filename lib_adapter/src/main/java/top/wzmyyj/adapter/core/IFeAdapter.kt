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
 * @version 1.0.0
 * @since 1.0.0
 */
@MainThread
interface IFeAdapter<M : IVhModelType> {

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
     * Init ViewTypeDelegateManager. You can add VTDs.
     */
    fun initManager(manager: ViewTypeDelegateManager<M>) {}

    /**
     * Set data list.
     */
    fun setList(list: List<M>)

    /**
     * Get data list.
     */
    fun getList(): List<M>

    /**
     * Get item by position.
     */
    fun getItem(position: Int): M? = getList().getOrNull(position)

    /**
     * Refresh some items.
     */
    fun refreshItems(items: List<M>)

}