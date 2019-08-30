package top.wzmyyj.feadapter.base

import androidx.databinding.ViewDataBinding

/**
 * Created on 2019/08/30.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
interface IVD<M : IModelType> {

    fun isForViewType(viewType: Int):Boolean

    fun onCreateVH(binding: ViewDataBinding)

    fun onBindVH(binding: ViewDataBinding, m: M)
}