package top.wzmyyj.adapter.tree

/**
 * Created on 2019/09/01.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class TreeUtil {

    companion object {
        /**
         * 从一颗树中获取所以叶子节点。
         */
        fun <M : IModelTree<M>> leafChildren(tree: M): List<M> {
            return ArrayList<M>().also { list ->
                findLeafs(tree, list)
            }
        }

        /**
         * 递归遍历。
         */
        private fun <M : IModelTree<M>> findLeafs(tree: M, list: ArrayList<M>) {
            val childTreeList = tree.getChildren()
            if (childTreeList == null) {
                list.add(tree)
                return
            }
            for (child in childTreeList) {
                findLeafs(child, list)
            }
        }

    }
}