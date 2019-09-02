package top.wzmyyj.adapter.base

import androidx.databinding.ViewDataBinding

/**
 * Created on 2019/09/02.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
abstract class SingleItemViewDelegate<DB : ViewDataBinding, M : IModelType> : ItemViewDelegate<DB, M> {
    final override fun filter(viewType: Int): Boolean {
        return viewType == getViewType()
    }

    abstract fun getViewType(): Int

}