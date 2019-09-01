package top.wzmyyj.feadapter.adapter

import androidx.databinding.ViewDataBinding
import top.wzmyyj.feadapter.BR
import top.wzmyyj.adapter.base.BaseListAdapter
import top.wzmyyj.feadapter.model.IGoodsModelType
import top.wzmyyj.feadapter.model.UserItemModel

/**
 * Created on 2019/08/30.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class UserAdapter(private val listener: OnAdapterListener) : BaseListAdapter<IGoodsModelType>() {
    override fun onCreateVHForAll(binding: ViewDataBinding) {
        binding.setVariable(BR.listener, listener)
    }

    override fun onBindVHForAll(binding: ViewDataBinding, m: IGoodsModelType) {
        binding.setVariable(BR.item, m)
        binding.executePendingBindings()
    }


    interface OnAdapterListener : UserItemModel.OnItemListener
}