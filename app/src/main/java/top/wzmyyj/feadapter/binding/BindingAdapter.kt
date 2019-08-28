package top.wzmyyj.feadapter.binding

import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter

/**
 * Created on 2019/08/28.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
@BindingAdapter("fe_marginTop")
fun View.setMarginTop(marginTop: Int) {
    if (this.layoutParams is ViewGroup.MarginLayoutParams) {
        val p = this.layoutParams as ViewGroup.MarginLayoutParams
        p.topMargin = marginTop
//        this.requestLayout()
    }
}