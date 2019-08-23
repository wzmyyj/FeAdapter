package top.wzmyyj.feadapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import top.wzmyyj.feadapter.R
import top.wzmyyj.feadapter.base.BaseViewHolder
import top.wzmyyj.feadapter.databinding.XxItem13Binding
import top.wzmyyj.feadapter.model.XXItem13Model

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class XXItem13VH(
        private val iBinding: XxItem13Binding
) : BaseViewHolder<XXItem13Model>(iBinding) {

    override fun bindVH(m: XXItem13Model) {
        iBinding.run {
            item = m
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup) = XXItem13VH(
                DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                        R.layout.xx_item_13, parent, false))
    }
}