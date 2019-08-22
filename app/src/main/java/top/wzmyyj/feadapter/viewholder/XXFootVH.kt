package top.wzmyyj.feadapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import top.wzmyyj.feadapter.R
import top.wzmyyj.feadapter.base.BaseViewHolder
import top.wzmyyj.feadapter.databinding.XxFootBinding
import top.wzmyyj.feadapter.model.XXFootModel

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class XXFootVH(
        private val iBinding: XxFootBinding
) : BaseViewHolder<XXFootModel>(iBinding) {

    override fun bindVH(m: XXFootModel) {
        iBinding.run {
            item = m
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup) = XXFootVH(
                DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                        R.layout.xx_foot, parent, false))
    }
}