package top.wzmyyj.feadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import top.wzmyyj.feadapter.adapter.TrendAdapter
import top.wzmyyj.feadapter.model.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rv)
        val xxAdapter = TrendAdapter(listener)
        recyclerView.adapter = xxAdapter


        val head = TrendHeadModel()
        head.txt = "这是头部！"


        val foot = TrendFootModel()
        foot.txt = "这是底部！"


        val empty = TrendEmptyModel()
        empty.txt = "这是空数据！"

        val item111 = MaterialPic2Model()
        item111.txt = "这是item111！"


        val item112 = MaterialPic3Model()
        item112.txt = "这是item112！"


        val item11 = MaterialPicModelWrapper()
        item11.item111 = item111
        item11.item112 = item112


        val item12 = LinkMeetingModel()
        item12.txt = "这是item12！"
        val item12s = LinkMeetingModel()
        item12s.txt = "这也是item12！"

        val item12List = ArrayList<LinkMeetingModel>()
        item12List.add(item12)
        item12List.add(item12s)

        val item13 = MaterialTextModel()
        item13.txt = "这是item13！"

        val item1 = MaterialModelWrapper()
        item1.item11 = item11
        item1.item12List = item12List
        item1.item13 = item13

        val item2 = LinkGoodsModel()
        item2.txt = "这是item2！"

        val item31 = XXItem31Model()
        item31.txt = "这是item31！"

        val item32 = XXItem32Model()
        item32.txt = "这是item32！"

        val item3 = XXItem3ModelWrapper()
        item3.item31 = item31
        item3.item32 = item32

        val item4 = UserListModel()
        item4.txt = "这是item4！"

        item4.list.clear()
        item4.list.add(UserItemModel())
        item4.list.add(UserItemModel())
        item4.list.add(UserItemModel())
        item4.list.add(UserItemModel())
        item4.list.add(UserItemModel())
        item4.list.add(UserItemModel())


        val list = ArrayList<IFeedModelType>()
        list.add(head)
        list.add(item1)
        list.add(item2)
        list.add(item3)
        list.add(item4)
        list.add(empty)
        list.add(foot)

        xxAdapter.setData(list)

    }


    private val listener = object : TrendAdapter.OnAdapterListener {
        override fun onYY(model: UserItemModel) {
            Toast.makeText(application, model.txt, Toast.LENGTH_SHORT).show()
        }

        override fun onHead(model: TrendHeadModel) {
            Toast.makeText(application, model.txt, Toast.LENGTH_SHORT).show()
        }

        override fun onEmpty(model: TrendEmptyModel) {
            Toast.makeText(application, model.txt, Toast.LENGTH_SHORT).show()
        }

    }
}
