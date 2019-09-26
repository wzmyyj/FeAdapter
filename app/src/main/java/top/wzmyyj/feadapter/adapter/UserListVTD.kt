package top.wzmyyj.feadapter.adapter

import top.wzmyyj.adapter.base.ViewTypeDelegate
import top.wzmyyj.feadapter.R
import top.wzmyyj.feadapter.databinding.TrendUserListBinding
import top.wzmyyj.feadapter.model.UserListModel

/**
 * Created on 2019/08/30.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class UserListVTD(private val listener: UserAdapter.OnAdapterListener) :
    ViewTypeDelegate<TrendUserListBinding, UserListModel> {

    override fun getViewType(): Int {
        return R.layout.trend_user_list
    }

    override fun onCreateVH(binding: TrendUserListBinding) {
        binding.rv4.adapter = UserAdapter(listener)
    }

    override fun onBindVH(binding: TrendUserListBinding, m: UserListModel) {
        (binding.rv4.adapter as? UserAdapter)?.setData(m.list)
    }

}