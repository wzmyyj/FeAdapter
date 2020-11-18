package top.wzmyyj.adapter.core

import java.lang.RuntimeException

/**
 * Created on 2020/10/23.
 *
 * Model wrapper. M type need same as constrained {@link IVhModelType}.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 * @see IVhModelType
 */
interface IVhModelWrapper<M : IVhModelType> : IVhModelType {

    override fun getViewType(): Int = throw RuntimeException("Wrapper can not getViewType()!")

    /**
     * As sub model list.
     */
    fun asList(): List<M>
}