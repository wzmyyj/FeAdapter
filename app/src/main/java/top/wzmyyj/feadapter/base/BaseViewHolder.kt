package top.wzmyyj.feadapter.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
abstract class BaseViewHolder<M : IModelType>(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bindVH(m: M)
}