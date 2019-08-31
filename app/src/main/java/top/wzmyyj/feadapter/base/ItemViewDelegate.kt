package top.wzmyyj.feadapter.base

import androidx.databinding.ViewDataBinding

/**
 * Created on 2019/08/30.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
interface ItemViewDelegate<DB : ViewDataBinding, M : IModelType> {

    fun isForViewType(viewType: Int): Boolean

    fun onCreateVH(binding: DB)

    fun onBindVH(binding: DB, m: M)
}