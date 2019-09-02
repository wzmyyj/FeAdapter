package top.wzmyyj.adapter.base

import androidx.databinding.ViewDataBinding

/**
 * Created on 2019/08/30.
 * 每个Item对View的处理委托（接口）。
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
interface ItemViewDelegate<DB : ViewDataBinding, M : IModelType> {

    /**
     * viewType满足某添加后委托生效。
     * @return viewType。
     */
    fun getViewType(): Int

    /**
     * 当创建ViewHolder时。做什么事。
     * @param binding ViewDataBinding
     */
    fun onCreateVH(binding: DB)

    /**
     * 当Bind ViewHolder时。做什么事。
     * @param binding ViewDataBinding
     * @param m 具体的Model。
     */
    fun onBindVH(binding: DB, m: M)
}