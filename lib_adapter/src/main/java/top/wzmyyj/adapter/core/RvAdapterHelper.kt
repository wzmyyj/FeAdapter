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
 * @version 1.0.1
 * @since 1.0.0
 * @see IRvAdapter
 * @see RvAdapter
 */
class RvAdapterHelper<M : IVhModelType>(private val adapter: IRvAdapter<M>) {

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
    fun onBindViewHolder(holder: BindingViewHolder, position: Int, payloads: List<Any?>): Boolean {
        val payload = payloads.firstOrNull() ?: return false
        val item = adapter.getModel(position) ?: return false
        setFullSpan(holder, item)
        if (ivdManager.onBindVH(holder.binding, item, payload)) {
            adapter.afterBindVH(holder.binding, item)
            return true
        }
        return false
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val item = adapter.getModel(position) ?: return
        setFullSpan(holder, item)
        adapter.onBindVHForAll(holder.binding, item)
        ivdManager.onBindVH(holder.binding, item)
        holder.binding.executePendingBindings()
        adapter.afterBindVH(holder.binding, item)
    }

    /**
     * Compare the list to find the same items and notify adapter.
     * Because the wrapper length may change. First and last as the basis.
     * It doesn't matter if the middle one is changed.
     * Make sure the head and tail remain the same are OK.
     */
    fun compareItems(items: Array<out M>, dataList: List<M>, notify: (Int, Int) -> Unit) {
        items.forEach { m ->
            if (m is IVhModelWrapper<*>) {
                val f = ArrayList<M>()
                findLeaf(m, f)
                if (f.isEmpty()) return@forEach
                val l = dataList.indexOf(f.first())
                if (l == -1) return@forEach
                val r = dataList.indexOf(f.last())
                if (r == -1) return@forEach
                val c = r - l
                if (c < 1) return@forEach
                notify(l, c)
            }
            val l = dataList.indexOf(m)
            if (l == -1) return@forEach
            notify(l, 1)
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

    //--------------private method----------------//

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
                    val item = adapter.getModel(position)
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