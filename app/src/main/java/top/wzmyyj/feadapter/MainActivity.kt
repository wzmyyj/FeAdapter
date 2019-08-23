package top.wzmyyj.feadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import top.wzmyyj.feadapter.adapter.XXAdapter
import top.wzmyyj.feadapter.model.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rv)
        val xxAdapter = XXAdapter()
        recyclerView.adapter = xxAdapter


        val head = XXHeadModel()
        head.txt = "这是头部！"


        val foot = XXFootModel()
        foot.txt = "这是底部！"


        val empty = XXEmptyModel()
        empty.txt = "这是空数据！"

        val item111 = XXItem111Model()
        item111.txt = "这是item111！"


        val item112 = XXItem112Model()
        item112.txt = "这是item112！"


        val item11 = XXItem11ModelWrapper()
        item11.item111 = item111
        item11.item112 = item112


        val item12 = XXItem12Model()
        item12.txt = "这是item12！"
        val item12s = XXItem12Model()
        item12s.txt = "这也是item12！"

        val item12List = ArrayList<XXItem12Model>()
        item12List.add(item12)
        item12List.add(item12s)

        val item13 = XXItem13Model()
        item13.txt = "这是item13！"

        val item1 = XXItem1ModelWrapper()
        item1.item11 = item11
        item1.item12List = item12List
        item1.item13 = item13

        val item2 = XXItem2Model()
        item2.txt = "这是item2！"

        val item31 = XXItem31Model()
        item31.txt = "这是item31！"

        val item32 = XXItem32Model()
        item32.txt = "这是item32！"

        val item3 = XXItem3ModelWrapper()
        item3.item31 = item31
        item3.item32 = item32

        val item4 = XXItem4Model()
        item4.txt = "这是item4！"


        val list = ArrayList<IXXModelType>()
        list.add(head)
        list.add(item1)
        list.add(item2)
        list.add(item3)
        list.add(item4)
        list.add(empty)
        list.add(foot)

        xxAdapter.setData(list)

    }
}
