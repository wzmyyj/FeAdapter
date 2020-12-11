package top.wzmyyj.feadapter.model

import top.wzmyyj.diff_annotation.SameContent
import top.wzmyyj.diff_annotation.SameItem

/**
 * Created on 2020/12/10.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
class YyModel {

    @SameItem
    var id: Long = 0

    @SameContent
    var title: String? = null

    // no comparison
    var isx:Boolean= false

}