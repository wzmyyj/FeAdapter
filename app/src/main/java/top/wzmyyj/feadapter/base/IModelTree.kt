package top.wzmyyj.feadapter.base

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
interface IModelTree<M : IModelTree<M>> : IModelType {
    fun getChildren(): List<M>? = null

    companion object {
        fun <M : IModelTree<M>> leafChildren(tree: M): List<M> {
            return ArrayList<M>().also { list ->
                findLeafs(tree, list)
            }
        }

        private fun <M : IModelTree<M>> findLeafs(tree: M ,list: ArrayList<M>) {
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