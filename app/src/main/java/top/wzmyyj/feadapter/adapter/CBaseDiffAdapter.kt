package top.wzmyyj.feadapter.adapter

import androidx.databinding.ViewDataBinding
import top.wzmyyj.adapter.core.IVhModelType
import top.wzmyyj.adapter.diff.DiffListAdapter

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
}