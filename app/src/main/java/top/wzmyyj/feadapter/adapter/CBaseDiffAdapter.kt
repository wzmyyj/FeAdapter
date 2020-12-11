package top.wzmyyj.feadapter.adapter

import androidx.databinding.ViewDataBinding
import top.wzmyyj.adapter.core.BindingViewHolder
import top.wzmyyj.adapter.core.IVhModelType
import top.wzmyyj.adapter.diff.DiffListAdapter
import top.wzmyyj.diff_api.Payload

/**
 * Created on 2020/12/09.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
abstract class CBaseDiffAdapter<M : IVhModelType>(private val callback: DiffModelCallback<M>) :
    DiffListAdapter<M>(callback) {

    override fun afterBindVH(binding: ViewDataBinding, m: M) {
        callback.bindNewData(binding, m)
    }

//    override fun onBindViewHolder(holder: BindingViewHolder, position: Int, payloads: MutableList<Any>) {
//        val payload = payloads.firstOrNull() as? Payload
//        if (payload != null && payload.isEmpty.not()) {
//            // do local refresh according to payload.
//            val newAttr = payload.getString("key", "xxx")
//            holder.itemView.tv.text = newAttr
//        } else {
//            super.onBindViewHolder(holder, position, payloads)
//        }
//        // after onBindViewHolder.
//        callback.bindNewData(holder, getItem(position))
//        // or callback.bindNewData(holder.itemView, getItem(position))
//    }
}