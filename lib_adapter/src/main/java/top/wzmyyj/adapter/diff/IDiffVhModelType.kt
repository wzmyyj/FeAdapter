package top.wzmyyj.adapter.diff

import top.wzmyyj.adapter.core.IVhModelType

/**
 * Created on 2020/10/22.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
interface IDiffVhModelType : IVhModelType {

    /**
     * Whether the content is the same. By default, the content always different.
     *
     * @param other other model
     */
    fun areContentsTheSame(other: IDiffVhModelType): Boolean {
        return false
    }

    /**
     * Whether the item is the same. By default, the same object is the same item.
     *
     * @param other other model
     */
    fun areItemsTheSame(other: IDiffVhModelType): Boolean {
        return this == other
    }

}