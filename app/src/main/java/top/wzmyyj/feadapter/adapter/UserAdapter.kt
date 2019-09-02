package top.wzmyyj.feadapter.adapter

import androidx.databinding.ViewDataBinding
import top.wzmyyj.feadapter.BR
import top.wzmyyj.adapter.base.BaseListAdapter
import top.wzmyyj.feadapter.model.IUserModelType
import top.wzmyyj.feadapter.model.UserItemModel

/**
 * Created on 2019/08/30.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class UserAdapter(private val listener: OnAdapterListener) : BaseListAdapter<IUserModelType>() {
    override fun onCreateVHForAll(binding: ViewDataBinding) {
        binding.setVariable(BR.listener, listener)
    }

    override fun onBindVHForAll(binding: ViewDataBinding, m: IUserModelType) {
        binding.setVariable(BR.item, m)
    }


    interface OnAdapterListener : UserItemModel.OnItemListener
}