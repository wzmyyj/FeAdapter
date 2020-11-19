package top.wzmyyj.adapter.core

import androidx.annotation.MainThread

/**
 * Created on 2020/11/19.
 *
 * @author feling
 * @version 1.0.1
 * @since 1.0.1
 */
@MainThread
interface IRvAdapterMutable<M : IVhModelType> {

    /**
     * Set data list.
     */
    fun setList(list: List<M>)

    /**
     * Get data list.
     */
    fun getList(): List<M>

    /**
     * Refresh some items.
     */
    fun refreshItems(vararg items: M)

    /**
     * Remove some items.
     */
    fun removeItems(vararg items: M)

}