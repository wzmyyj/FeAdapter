package top.wzmyyj.adapter.core

import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import top.wzmyyj.adapter.core.ISpanSize.Companion.SPAN_SIZE_FULL
import top.wzmyyj.adapter.core.ISpanSize.Companion.SPAN_SIZE_SINGLE

/**
 * Created on 2020/10/22.
 *
 * FeAdapterHelper. It can be easily used in adapter.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 * @see IFeAdapter
 * @see FeAdapter
 */
class FeAdapterHelper<M : IVhModelType>(private val adapter: IFeAdapter<M>) {

    private val ivdManager: ViewTypeDelegateManager<M> = ViewTypeDelegateManager()

    /**
     * Called when RecyclerView starts observing this Adapter.
     */
    fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        adapter.initManager(ivdManager)
        fixSpanSize(recyclerView)
    }

    /**
     * Called when RecyclerView stops observing this Adapter.
     */
    fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        ivdManager.clear()
        recyclerView.adapter = null// fei hua!
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     */
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val holder = adapter.createVH(parent, viewType)
        adapter.onCreateVHForAll(holder.binding)
        ivdManager.onCreateVH(holder.binding, viewType)
        return holder
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val item = adapter.getItem(position) ?: return
        setFullSpan(holder, item)
        adapter.onBindVHForAll(holder.binding, item)
        ivdManager.onBindVH(holder.binding, item)
        holder.binding.executePendingBindings()
    }

    /**
     * Compare the list to find the same items and refresh them.
     */
    fun refreshItems(items: List<M>, dataList: List<M>, notify: (Int) -> Unit) {
        val li = transform(items)
        for (m in li) {
            if (m in dataList) {
                notify(dataList.indexOf(m))
            }
        }
    }

    /**
     * Transform data list. Always return a new list.
     */
    fun transform(original: List<M>): List<M> {
        val result = ArrayList<M>()
        original.forEach { findLeaf(it, result) }
        return result
    }

    /**
     * Recursively traversing all leaf nodes.
     */
    @Suppress("UNCHECKED_CAST")
    private fun findLeaf(model: M, list: ArrayList<M>) {
        if (model is IVhModelWrapper<*>) {
            model.asList().forEach { findLeaf(it as M, list) }
        } else {
            list.add(model)
        }
    }

    /**
     * Fix span size when recyclerView's layoutManager is GridLayoutManager.
     *
     * @see GridLayoutManager
     */
    private fun fixSpanSize(recyclerView: RecyclerView) {
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is GridLayoutManager) {
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val item = adapter.getItem(position)
                    if (item is ISpanSize) {
                        val spanSize = item.getSpanSize()
                        return if (spanSize == SPAN_SIZE_FULL) layoutManager.spanCount else spanSize
                    }
                    return SPAN_SIZE_SINGLE
                }
            }
        }
    }

    /**
     * Set full span when recyclerView's layoutManager is StaggeredGridLayoutManager.
     *
     * @see StaggeredGridLayoutManager
     */
    private fun setFullSpan(holder: RecyclerView.ViewHolder, item: M) {
        val lp = holder.itemView.layoutParams ?: return
        if (lp is StaggeredGridLayoutManager.LayoutParams) {
            lp.isFullSpan = item is ISpanSize && item.getSpanSize() == SPAN_SIZE_FULL
        }
    }

}