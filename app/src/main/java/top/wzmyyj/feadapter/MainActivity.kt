package top.wzmyyj.feadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import top.wzmyyj.feadapter.adapter.TrendAdapter
import top.wzmyyj.feadapter.databinding.LinkGoodsBinding
import top.wzmyyj.feadapter.model.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rv)
        val xxAdapter = TrendAdapter(listener)
        recyclerView.adapter = xxAdapter


        val head = TrendHeadModel()
        val foot = TrendFootModel()
        val empty = TrendEmptyModel()


        val publisher = MaterialPublisherModel()
        val text = MaterialTextModel()
        val pic = MaterialPicModelWrapper()
        val link = MaterialLinkModelWrapper()
        val bottom = MaterialBottomModel()


        val material = MaterialModelWrapper()
        material.publisher = publisher
        material.text = text
        material.pic = pic
        material.link = link
        material.bottom = bottom


        val pic2List = ArrayList<MaterialPic2Model>()
        val pic2 = MaterialPic2Model()
        pic2List.add(pic2)
        pic2List.add(pic2)

        //或者
//        val pic3List = ArrayList<MaterialPic3Model>()
//        val pic3 = MaterialPic3Model()
//        pic3List.add(pic3)
//        pic3List.add(pic3)
//        pic3List.add(pic3)


        pic.pic2List = pic2List

        val linkGoods = MaterialLinkGoodsModel()
        link.linkGoods = linkGoods

        //或者
//        val linkMeeting = MaterialLinkMeetingModel()
//        link.linkMeeting = linkMeeting


        val userList = UserListModel()

        val users = ArrayList<UserItemModel>()
        users.add(UserItemModel())
        users.add(UserItemModel())
        users.add(UserItemModel())
        users.add(UserItemModel())
        users.add(UserItemModel())
        users.add(UserItemModel())
        userList.list.clear()
        userList.list.addAll(users)

        val list = ArrayList<IFeedModelType>()
        list.add(head)
        list.add(userList)
        list.add(material)
        list.add(material)
        list.add(material)
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
