package top.wzmyyj.feadapter.adapter

import androidx.databinding.ViewDataBinding
import top.wzmyyj.feadapter.BR
import top.wzmyyj.feadapter.base.BaseListAdapter
import top.wzmyyj.feadapter.model.IYYModelType
import top.wzmyyj.feadapter.model.YYItemModel

/**
 * Created on 2019/08/30.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class YYAdapter(private val listener: OnAdapterListener) : BaseListAdapter<IYYModelType>() {
    override fun onCreateVHForAll(binding: ViewDataBinding) {
        binding.setVariable(BR.listener, listener)
    }

    override fun onBindVHForAll(binding: ViewDataBinding, m: IYYModelType) {
        binding.setVariable(BR.item, m)
        binding.executePendingBindings()
    }


    interface OnAdapterListener : YYItemModel.OnItemListener
}