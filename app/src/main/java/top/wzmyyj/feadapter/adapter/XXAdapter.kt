package top.wzmyyj.feadapter.adapter

import androidx.databinding.ViewDataBinding
import top.wzmyyj.feadapter.BR
import top.wzmyyj.feadapter.base.BaseTreeAdapter
import top.wzmyyj.feadapter.base.ItemViewDelegate
import top.wzmyyj.feadapter.base.ItemViewDelegateManager
import top.wzmyyj.feadapter.model.IXXModelType
import top.wzmyyj.feadapter.model.XXEmptyModel
import top.wzmyyj.feadapter.model.XXHeadModel

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class XXAdapter(private val listener: OnAdapterListener) : BaseTreeAdapter<IXXModelType>() {
    override fun onCreateVHForAll(binding: ViewDataBinding) {
        binding.setVariable(BR.listener, listener)
    }

    override fun onBindVHForAll(binding: ViewDataBinding, m: IXXModelType) {
        binding.setVariable(BR.item, m)
        binding.executePendingBindings()
    }


    override fun initIvdList(manager: ItemViewDelegateManager<IXXModelType>) {
        super.initIvdList(manager)
        manager.addIvd(XXItem4IVD(listener))
    }


    interface OnAdapterListener :
        XXHeadModel.OnItemListener,
        XXEmptyModel.OnItemListener,
        YYAdapter.OnAdapterListener
}