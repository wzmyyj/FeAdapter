package top.wzmyyj.feadapter.adapter

import androidx.recyclerview.widget.DiffUtil
import top.wzmyyj.adapter.core.IVhModelType
import top.wzmyyj.diff_api.DiffModelHelper

/**
 * Created on 2020/12/09.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
class DiffModelCallback<M : IVhModelType> : DiffUtil.ItemCallback<M>() {

    private val helper = DiffModelHelper()

    fun getHelper(): DiffModelHelper = helper

    fun bindNewData(bindObj: Any, newModel: M) {
        helper.bindNewData(bindObj, newModel)
    }

    override fun areItemsTheSame(oldItem: M, newItem: M): Boolean {
        return helper.isSameItem(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: M, newItem: M): Boolean {
        return helper.isSameContent(oldItem, newItem)
    }

    override fun getChangePayload(oldItem: M, newItem: M): Any? {
        return helper.getPayload(oldItem, newItem)
    }
}