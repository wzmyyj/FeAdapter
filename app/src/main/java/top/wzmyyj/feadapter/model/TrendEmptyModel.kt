package top.wzmyyj.feadapter.model

import top.wzmyyj.feadapter.R

/**
 * Created on 2019/08/22.
 *
 * @author feling
 * @version 1.0
 * @since 1.0
 */
class TrendEmptyModel(

    var txt: String = ""

) : IFeedModelType {

    override fun getViewType(): Int {
        return R.layout.trend_empty
    }

    interface OnItemListener {
        fun onEmpty(model: TrendEmptyModel)
    }
}