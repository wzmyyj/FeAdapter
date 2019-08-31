package top.wzmyyj.feadapter.base

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
abstract class BaseListAdapter<M : IModelType> : RecyclerView.Adapter<BaseListAdapter.ViewHolder>() {
    private val items: MutableList<M> = ArrayList()

    private val ivdManager: ItemViewDelegateManager<M> = ItemViewDelegateManager()

    class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        initIvdList(ivdManager)
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        ivdManager.clear()
        items.clear()
        super.onDetachedFromRecyclerView(recyclerView)
    }


    abstract fun onCreateVHForAll(binding: ViewDataBinding)

    abstract fun onBindVHForAll(binding: ViewDataBinding, m: M)

    protected open fun initIvdList(manager: ItemViewDelegateManager<M>) {
        manager.addIvd(object : ItemViewDelegate<ViewDataBinding, M> {
            override fun isForViewType(viewType: Int): Boolean {
                return true
            }

            override fun onCreateVH(binding: ViewDataBinding) {
                onCreateVHForAll(binding)
            }

            override fun onBindVH(binding: ViewDataBinding, m: M) {
                onBindVHForAll(binding, m)
            }

        })
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            viewType, parent, false
        )
        ivdManager.onCreateVH(viewType, binding)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        ivdManager.onBindVH(items[position].getViewType(), holder.binding, items[position])
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