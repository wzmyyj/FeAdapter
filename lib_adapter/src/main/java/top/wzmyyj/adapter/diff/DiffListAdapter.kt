package top.wzmyyj.adapter.diff

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import top.wzmyyj.adapter.core.*

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
abstract class DiffListAdapter<M : IVhModelType>(callback: DiffUtil.ItemCallback<M>) :
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

    override fun onBindViewHolder(
        holder: BindingViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (helper.onBindViewHolder(holder, position, payloads)) return
        super.onBindViewHolder(holder, position, payloads)
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
    override fun refreshItems(items: List<M>) {
        helper.compareItems(items, readOnlyList) { l, c ->
            notifyItemRangeChanged(l, c)
        }
    }

    /**
     * Please do not use it with setList!
     */
    override fun removeItems(items: List<M>) {
        helper.compareItems(items, readOnlyList) { l, c ->
            for (i in l until l + c) readOnlyList.removeAt(i)
            notifyItemRangeRemoved(l, c)
        }
    }

}