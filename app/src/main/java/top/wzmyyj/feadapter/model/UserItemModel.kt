package top.wzmyyj.feadapter.model

import top.wzmyyj.feadapter.R

/**
 * Created on 2019/08/30.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class UserItemModel : IUserModelType {
    override fun getViewType(): Int {
        return R.layout.user_item
    }

    var txt: String = "用户"


    interface OnItemListener {
        fun onYY(model: UserItemModel)
    }
}