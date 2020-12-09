package top.wzmyyj.adapter.core

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created on 2020/10/22.
 *
 * Simple FeAdapter. You can write your own {@link RecyclerView.Adapter} according to this.
 *
 * @author feling
 * @version 1.0.1
 * @since 1.0.0
 * @see RecyclerView.Adapter
 * @see RvAdapterHelper
 * @see IRvAdapter
 */
abstract class RvAdapter<M : IVhModelType> : RecyclerView.Adapter<BindingViewHolder>(),
    IRvAdapter<M>, IRvAdapterMutable<M> {

    private val helper by lazy { RvAdapterHelper(this) }

    private val dataList = ArrayList<M>()

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

    override fun getItemViewType(position: Int): Int {
        return dataList[position].getViewType()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun setList(list: List<M>) {
        val li = helper.transform(list)
        dataList.clear()
        dataList.addAll(li)
        notifyDataSetChanged()
    }

    override fun getList(): List<M> {
        return dataList
    }

    override fun refreshItems(vararg items: M) {
        helper.compareItems(items, dataList) { l, c ->
            notifyItemRangeChanged(l, c)
        }
    }

    override fun removeItems(vararg items: M) {
        helper.compareItems(items, dataList) { l, c ->
            for (i in l until l + c) dataList.removeAt(i)
            notifyItemRangeRemoved(l, c)
        }
    }

}