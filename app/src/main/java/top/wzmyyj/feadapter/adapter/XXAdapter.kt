package top.wzmyyj.feadapter.adapter

import android.view.ViewGroup
import top.wzmyyj.feadapter.base.BaseTreeAdapter
import top.wzmyyj.feadapter.base.BaseViewHolder
import top.wzmyyj.feadapter.model.IXXModelType
import top.wzmyyj.feadapter.viewholder.*

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class XXAdapter : BaseTreeAdapter<IXXModelType>() {

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<IXXModelType> {
        return when (viewType) {
            IXXModelType.VIEW_TYPE_HEAD -> XXHeadVH.create(parent) as BaseViewHolder<IXXModelType>
            IXXModelType.VIEW_TYPE_FOOT -> XXFootVH.create(parent) as BaseViewHolder<IXXModelType>
            IXXModelType.VIEW_TYPE_EMPTY -> XXEmptyVH.create(parent) as BaseViewHolder<IXXModelType>
            IXXModelType.VIEW_TYPE_ITEM_1_1_1 -> XXItem111VH.create(parent) as BaseViewHolder<IXXModelType>
            IXXModelType.VIEW_TYPE_ITEM_1_1_2 -> XXItem112VH.create(parent) as BaseViewHolder<IXXModelType>
            IXXModelType.VIEW_TYPE_ITEM_1_2 -> XXItem12VH.create(parent) as BaseViewHolder<IXXModelType>
            IXXModelType.VIEW_TYPE_ITEM_1_3 -> XXItem13VH.create(parent) as BaseViewHolder<IXXModelType>
            IXXModelType.VIEW_TYPE_ITEM_2 -> XXItem2VH.create(parent) as BaseViewHolder<IXXModelType>
            IXXModelType.VIEW_TYPE_ITEM_3_1 -> XXItem31VH.create(parent) as BaseViewHolder<IXXModelType>
            IXXModelType.VIEW_TYPE_ITEM_3_2 -> XXItem32VH.create(parent) as BaseViewHolder<IXXModelType>
            IXXModelType.VIEW_TYPE_ITEM_4 -> XXItem4VH.create(parent) as BaseViewHolder<IXXModelType>
            else -> throw RuntimeException("no such type view holder!")
        }
    }
}