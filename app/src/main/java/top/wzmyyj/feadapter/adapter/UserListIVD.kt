package top.wzmyyj.feadapter.adapter

import top.wzmyyj.feadapter.R
import top.wzmyyj.adapter.base.ItemViewDelegate
import top.wzmyyj.feadapter.databinding.TrendUserListBinding
import top.wzmyyj.feadapter.model.UserListModel

/**
 * Created on 2019/08/30.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class UserListIVD(private val listener: UserAdapter.OnAdapterListener) :
    ItemViewDelegate<TrendUserListBinding, UserListModel> {
    override fun isForViewType(viewType: Int): Boolean {
        return viewType == R.layout.trend_user_list
    }

    override fun onCreateVH(binding: TrendUserListBinding) {
    }

    override fun onBindVH(binding: TrendUserListBinding, m: UserListModel) {
        binding.rv4.adapter = yyAdapter
        yyAdapter.setData(m.list)
    }

    private val yyAdapter by lazy {
        UserAdapter(listener)
    }
}