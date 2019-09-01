package top.wzmyyj.feadapter.adapter

import androidx.databinding.ViewDataBinding
import top.wzmyyj.feadapter.BR
import top.wzmyyj.adapter.tree.BaseTreeAdapter
import top.wzmyyj.adapter.base.ItemViewDelegateManager
import top.wzmyyj.feadapter.model.IFeedModelType
import top.wzmyyj.feadapter.model.TrendEmptyModel
import top.wzmyyj.feadapter.model.TrendHeadModel

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class TrendAdapter(private val listener: OnAdapterListener) : BaseTreeAdapter<IFeedModelType>() {
    override fun onCreateVHForAll(binding: ViewDataBinding) {
        binding.setVariable(BR.listener, listener)
    }

    override fun onBindVHForAll(binding: ViewDataBinding, m: IFeedModelType) {
        binding.setVariable(BR.item, m)
        binding.executePendingBindings()
    }


    override fun initIVDs(manager: ItemViewDelegateManager<IFeedModelType>) {
        super.initIVDs(manager)
        manager.addIvd(UserListIVD(listener))
    }


    interface OnAdapterListener :
        TrendHeadModel.OnItemListener,
        TrendEmptyModel.OnItemListener,
        UserAdapter.OnAdapterListener
}