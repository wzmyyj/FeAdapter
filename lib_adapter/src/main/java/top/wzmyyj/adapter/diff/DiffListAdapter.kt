package top.wzmyyj.adapter.diff

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import top.wzmyyj.adapter.core.BindingViewHolder
import top.wzmyyj.adapter.core.RvAdapterHelper
import top.wzmyyj.adapter.core.IRvAdapter
import top.wzmyyj.adapter.core.IRvAdapterMutable

/**
 * Created on 2020/10/22.
 *
 * DiffListAdapter. This adapter used {@link DiffUtil}.
 *
 * @author feling
 * @version 1.0.1
 * @since 1.0.0
 * @see ListAdapter
 * @see DiffUtil
 * @see RvAdapterHelper
 * @see IRvAdapter
 */
abstract class DiffListAdapter<M : IDiffVhModelType>(callback: DiffCallBack<M>) :
    ListAdapter<M, BindingViewHolder>(callback), IRvAdapter<M>, IRvAdapterMutable<M> {

    private val helper by lazy { RvAdapterHelper(this) }

    private val readOnlyList = ArrayList<M>()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        helper.onAttachedToRecyclerView(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        helper.onDetachedFromRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        return helper.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        helper.onBindViewHolder(holder, position)
    }

    override fun getModel(position: Int): M? {
        return getItem(position)
    }

    override fun getItemViewType(position: Int): Int {
        return getModel(position)?.getViewType() ?: 0
    }

    override fun setList(list: List<M>) {
        val li = helper.transform(list)
        readOnlyList.clear()
        readOnlyList.addAll(li)
        // Always submit a new list.
        submitList(li)
    }

    override fun getList(): List<M> {
        // Just for read.
        return readOnlyList
    }

    /**
     * Please do not use it with setList!
     */
    override fun refreshItems(vararg items: M) {
        helper.compareItems(items, readOnlyList) { l, c ->
            notifyItemRangeChanged(l, c)
        }
    }

    /**
     * Please do not use it with setList!
     */
    override fun removeItems(vararg items: M) {
        helper.compareItems(items, readOnlyList) { l, c ->
            for (i in l until l + c) readOnlyList.removeAt(i)
            notifyItemRangeRemoved(l, c)
        }
    }

    open class DiffCallBack<N : IDiffVhModelType> : DiffUtil.ItemCallback<N>() {

        override fun areItemsTheSame(oldItem: N, newItem: N): Boolean {
            // Judge whether two items use the same item.
            return newItem.areItemsTheSame(oldItem)
        }

        override fun areContentsTheSame(oldItem: N, newItem: N): Boolean {
            // Judge whether the contents of two items are the same.
            return newItem.areContentsTheSame(oldItem)
        }

        override fun getChangePayload(oldItem: N, newItem: N): Any? {
            // Advanced usage, used to extract changes and refresh local variables accurately.
            // Not for the time being.
            return null
        }
    }

}