package top.wzmyyj.feadapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import top.wzmyyj.feadapter.R
import top.wzmyyj.feadapter.base.BaseViewHolder
import top.wzmyyj.feadapter.databinding.XxItem112Binding
import top.wzmyyj.feadapter.model.XXItem112Model

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class XXItem112VH(
        private val iBinding: XxItem112Binding
) : BaseViewHolder<XXItem112Model>(iBinding) {

    override fun bindVH(m: XXItem112Model) {
        iBinding.run {
            item = m
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup) = XXItem112VH(
                DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                        R.layout.xx_item_112, parent, false))
    }
}