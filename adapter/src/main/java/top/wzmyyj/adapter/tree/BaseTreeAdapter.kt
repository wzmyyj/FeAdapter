package top.wzmyyj.adapter.tree

import top.wzmyyj.adapter.base.BaseListAdapter

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
abstract class BaseTreeAdapter<T : IModelTree<T>> : BaseListAdapter<T>() {
    override fun multiList(list: List<T>): List<T> {
        return ArrayList<T>().also { multiList ->
            list.forEach { tree ->
                multiList.addAll(TreeUtil.leafChildren(tree))
            }
        }
    }
}