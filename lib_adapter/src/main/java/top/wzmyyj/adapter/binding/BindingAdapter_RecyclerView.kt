package top.wzmyyj.adapter.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import top.wzmyyj.adapter.core.IRvAdapterMutable
import top.wzmyyj.adapter.core.IVhModelType

/**
 * Created on 2020/10/23.
 *
 * BindingAdapter of RecyclerView.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 * @see RecyclerView
 * @see IRvAdapterMutable
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter(value = ["binding_rv_dataList"], requireAll = true)
fun <M : IVhModelType> RecyclerView.bindingDataList(list: List<M>?) {
    val adapter = this.adapter as? IRvAdapterMutable<M> ?: return
    adapter.setList(list.orEmpty())
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter(value = ["binding_rv_refreshItems"], requireAll = true)
fun <M : IVhModelType> RecyclerView.bindingRefreshItems(items: List<M>?) {
    val adapter = this.adapter as? IRvAdapterMutable<M> ?: return
    adapter.refreshItems(items ?: return)
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter(value = ["binding_rv_removeItems"], requireAll = true)
fun <M : IVhModelType> RecyclerView.bindingRemoveItems(items: List<M>?) {
    val adapter = this.adapter as? IRvAdapterMutable<M> ?: return
    adapter.removeItems(items ?: return)
}