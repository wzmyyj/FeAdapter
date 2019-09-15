package top.wzmyyj.adapter.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
abstract class BaseListAdapter<M : IModelType> :
        RecyclerView.Adapter<BaseListAdapter.ViewHolder>() {
    private val items: MutableList<M> = ArrayList()

    private val ivdManager: ViewTypeDelegateManager<M> =
            ViewTypeDelegateManager()

    class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        addDelegate(ivdManager)
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        ivdManager.clear()
        items.clear()
        super.onDetachedFromRecyclerView(recyclerView)
    }


    abstract fun onCreateVHForAll(binding: ViewDataBinding)

    abstract fun onBindVHForAll(binding: ViewDataBinding, m: M)

    /**
     * 初始化各种viewType处理委托。添加到Manager中。
     */
    protected open fun addDelegate(manager: ViewTypeDelegateManager<M>) {
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                viewType, parent, false
        )
        onCreateVHForAll(binding)
        ivdManager.onCreateVH(binding, viewType)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBindVHForAll(holder.binding, items[position])
        ivdManager.onBindVH(holder.binding, items[position])
        holder.binding.executePendingBindings()
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

    /**
     * List数据结构需要改变时重写。即传入 List 经过变化后变成 items 需要的list。默认不改变结构。
     */
    protected open fun multiList(list: List<M>): List<M> {
        return list
    }
}