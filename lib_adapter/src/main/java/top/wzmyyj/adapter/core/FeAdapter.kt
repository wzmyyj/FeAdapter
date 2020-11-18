package top.wzmyyj.adapter.core

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created on 2020/10/22.
 *
 * Simple FeAdapter. You can write your own {@link RecyclerView.Adapter} according to this.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 * @see RecyclerView.Adapter
 * @see FeAdapterHelper
 * @see IFeAdapter
 */
abstract class FeAdapter<M : IVhModelType> : RecyclerView.Adapter<BindingViewHolder>(),
    IFeAdapter<M> {

    private val helper by lazy { FeAdapterHelper(this) }

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

    override fun refreshItems(items: List<M>) {
        helper.refreshItems(items, dataList) { position ->
            if (position in 0 until itemCount) {
                notifyItemChanged(position)
            }
        }
    }

}