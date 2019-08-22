package top.wzmyyj.feadapter.base

import androidx.recyclerview.widget.RecyclerView

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
abstract class BaseAdapter<M : IModelType> : RecyclerView.Adapter<BaseViewHolder<M>>() {
    private val items: MutableList<M> = ArrayList()

    override fun onBindViewHolder(holder: BaseViewHolder<M>, position: Int) {
        holder.bindVH(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].getViewType()
    }


    /**
     * 获取数据。
     */
    fun getData(): MutableList<M> {
        return items
    }

    /**
     * 设置数据
     */
    fun setData(list: List<M>) {
        val multiList = multiList(list)
        items.run {
            clear()
            addAll(multiList)
        }
        notifyDataSetChanged()
    }

    /**
     * 添加数据
     */
    fun addData(list: List<M>) {
        val multiList = multiList(list)
        val preSize = items.size
        items.run {
            addAll(multiList)
        }
        notifyItemRangeInserted(preSize, multiList.size)
    }


    /**
     * 只刷新局部数据。
     */
    fun changeData(vararg ms: M) {
        val multiList = multiList(ms.toList())
        for (m in multiList) {
            if (m in items) {
                val index = items.indexOf(m)
                notifyItemChanged(index)
            }
        }
    }


    /**
     * 只移除局部数据。
     */
    fun removeData(vararg ms: M) {
        val multiList = multiList(ms.toList())
        for (m in multiList) {
            if (m in items) {
                val index = items.indexOf(m)
                items.remove(m)
                notifyItemRemoved(index)
            }
        }
    }

    /**
     * 清空数据
     */
    fun clearData() {
        items.clear()
    }

    protected open fun multiList(list: List<M>): List<M> {
        return list
    }
}