package top.wzmyyj.adapter.diff

import top.wzmyyj.adapter.core.IVhModelType

/**
 * Created on 2020/10/22.
 *
 * @author feling
 * @version 1.0.1
 * @since 1.0.0
 */
interface IDiffVhModelType : IVhModelType {

    /**
     * Whether the item is the same. By default, the same object is the same item.
     *
     * @param old old model
     */
    fun areItemsTheSame(old: IDiffVhModelType): Boolean {
        return this == old
    }

    /**
     * Whether the content is the same. By default, the content always different.
     *
     * @param old old model
     */
    fun areContentsTheSame(old: IDiffVhModelType): Boolean {
        return false
    }

}