package top.wzmyyj.feadapter.adapter

import top.wzmyyj.feadapter.R
import top.wzmyyj.adapter.base.ItemViewDelegate
import top.wzmyyj.feadapter.databinding.XxItem4Binding
import top.wzmyyj.feadapter.model.UserListModel

/**
 * Created on 2019/08/30.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class UserListIVD(private val listener: UserAdapter.OnAdapterListener) :
    ItemViewDelegate<XxItem4Binding, UserListModel> {
    override fun isForViewType(viewType: Int): Boolean {
        return viewType == R.layout.trend_user_list
    }

    override fun onCreateVH(binding: XxItem4Binding) {
    }

    override fun onBindVH(binding: XxItem4Binding, m: UserListModel) {
        binding.rv4.adapter = yyAdapter
        yyAdapter.setData(m.list)
    }

    private val yyAdapter by lazy {
        UserAdapter(listener)
    }
}