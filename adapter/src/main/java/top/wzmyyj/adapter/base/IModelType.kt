package top.wzmyyj.adapter.base

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
interface IModelType {
    /**
     * 获取viewType。把layout id 当作viewType。
     */
    fun getViewType():Int
}