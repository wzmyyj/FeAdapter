package top.wzmyyj.feadapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import top.wzmyyj.feadapter.R
import top.wzmyyj.feadapter.base.BaseViewHolder
import top.wzmyyj.feadapter.databinding.XxItem2Binding
import top.wzmyyj.feadapter.model.XXItem2Model

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class XXItem2VH(
        private val iBinding: XxItem2Binding
) : BaseViewHolder<XXItem2Model>(iBinding) {

    override fun bindVH(m: XXItem2Model) {
        iBinding.run {
            item = m
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup) = XXItem2VH(
                DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                        R.layout.xx_item_2, parent, false))
    }
}