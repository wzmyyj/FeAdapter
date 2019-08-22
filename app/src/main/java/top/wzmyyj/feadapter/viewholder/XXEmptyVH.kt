package top.wzmyyj.feadapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import top.wzmyyj.feadapter.R
import top.wzmyyj.feadapter.base.BaseViewHolder
import top.wzmyyj.feadapter.databinding.XxEmptyBinding
import top.wzmyyj.feadapter.model.XXEmptyModel

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class XXEmptyVH(
        private val iBinding: XxEmptyBinding
) : BaseViewHolder<XXEmptyModel>(iBinding) {

    override fun bindVH(m: XXEmptyModel) {
        iBinding.run {
            item = m
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup) = XXEmptyVH(
                DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                        R.layout.xx_empty, parent, false))
    }
}