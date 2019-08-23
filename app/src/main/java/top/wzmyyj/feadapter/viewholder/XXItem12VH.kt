package top.wzmyyj.feadapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import top.wzmyyj.feadapter.R
import top.wzmyyj.feadapter.base.BaseViewHolder
import top.wzmyyj.feadapter.databinding.XxItem12Binding
import top.wzmyyj.feadapter.model.XXItem12Model

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class XXItem12VH(
        private val iBinding: XxItem12Binding
) : BaseViewHolder<XXItem12Model>(iBinding) {

    override fun bindVH(m: XXItem12Model) {
        iBinding.run {
            item = m
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup) = XXItem12VH(
                DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                        R.layout.xx_item_12, parent, false))
    }
}