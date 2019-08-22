package top.wzmyyj.feadapter.model

import top.wzmyyj.feadapter.base.IModelTree

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
interface IXXModelType : IModelTree<IXXModelType> {
    companion object {
        const val VIEW_TYPE_EMPTY = -1
        const val VIEW_TYPE_HEAD = 0
        const val VIEW_TYPE_FOOT = 10000

        const val VIEW_TYPE_ITEM_1_1_1 = 111
        const val VIEW_TYPE_ITEM_1_1_2 = 112
        const val VIEW_TYPE_ITEM_1_2 = 12
        const val VIEW_TYPE_ITEM_1_3 = 13

        const val VIEW_TYPE_ITEM_2 = 2
        const val VIEW_TYPE_ITEM_3_1 = 31
        const val VIEW_TYPE_ITEM_3_2 = 32
        const val VIEW_TYPE_ITEM_4 = 4

    }
}