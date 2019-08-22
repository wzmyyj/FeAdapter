package top.wzmyyj.feadapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import top.wzmyyj.feadapter.R
import top.wzmyyj.feadapter.base.BaseViewHolder
import top.wzmyyj.feadapter.databinding.XxHeadBinding
import top.wzmyyj.feadapter.model.XXHeadModel

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class XXHeadVH(
        private val iBinding: XxHeadBinding
) : BaseViewHolder<XXHeadModel>(iBinding) {

    override fun bindVH(m: XXHeadModel) {
        iBinding.run {
            item = m
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup) = XXHeadVH(
                DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                        R.layout.xx_head, parent, false))
    }
}