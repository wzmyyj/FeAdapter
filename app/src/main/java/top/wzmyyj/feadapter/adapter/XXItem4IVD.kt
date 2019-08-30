package top.wzmyyj.feadapter.adapter

import androidx.databinding.ViewDataBinding
import top.wzmyyj.feadapter.R
import top.wzmyyj.feadapter.base.IVD
import top.wzmyyj.feadapter.databinding.XxItem4Binding
import top.wzmyyj.feadapter.model.IXXModelType
import top.wzmyyj.feadapter.model.XXEmptyModel
import top.wzmyyj.feadapter.model.XXHeadModel
import top.wzmyyj.feadapter.model.XXItem4Model

/**
 * Created on 2019/08/30.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class XXItem4IVD(private val listener: YYAdapter.OnAdapterListener) : IVD<IXXModelType> {
    override fun isForViewType(viewType: Int): Boolean {
        return viewType == R.layout.xx_item_4
    }

    override fun onCreateVH(binding: ViewDataBinding) {
    }

    override fun onBindVH(binding: ViewDataBinding, m: IXXModelType) {
        if (binding is XxItem4Binding && m is XXItem4Model) {
            binding.rv4.adapter = yyAdapter
            yyAdapter.setData(m.list)
        }
    }

    private val yyAdapter by lazy {
        YYAdapter(listener)
    }
}