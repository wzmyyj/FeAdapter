package top.wzmyyj.adapter.tree

import top.wzmyyj.adapter.base.IModelType

/**
 * Created on 2019/08/22.
 * 树。
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
interface IModelTree<M : IModelTree<M>> : IModelType {
    /**
     * 获取子树列表。
     */
    fun getChildren(): List<M>? = null
}