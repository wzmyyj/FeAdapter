package top.wzmyyj.feadapter.model

import top.wzmyyj.diff_annotation.SameContent
import top.wzmyyj.diff_annotation.SameItem
import top.wzmyyj.diff_annotation.SameType

/**
 * Created on 2020/12/10.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
open class XxModel {
    @SameItem
    var id: Long = 0

    @SameContent
    var name: String? = null

    @SameContent("o1")
    var count = 0

    @SameContent("valid1")
    var valid = false

    @SameType
    var yy: YyModel? = null
}