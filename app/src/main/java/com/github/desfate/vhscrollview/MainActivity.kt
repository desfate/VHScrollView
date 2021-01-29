package com.github.desfate.vhscrollview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.desfate.vhscrollview.beans.ContentBean
import com.github.desfate.vhscrollview.models.ContentModel
import com.github.desfate.vhscrollview.models.TitleModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var rightList : ArrayList<String> = arrayListOf("title1", "title2", "title3", "title4", "title5", "title6", "title7")
    var leftTitle : ArrayList<TitleModel> = arrayListOf(TitleModel("goodsCode1", "goodsName1"),
        TitleModel("goodsCode2", "goodsName2"),
        TitleModel("goodsCode3", "goodsName3"),
        TitleModel("goodsCode4", "goodsName4"),
        TitleModel("goodsCode5", "goodsName5"),
        TitleModel("goodsCode6", "goodsName6"),
        TitleModel("goodsCode7", "goodsName7"),
        TitleModel("goodsCode8", "goodsName8"),
        TitleModel("goodsCode9", "goodsName9"),
        TitleModel("goodsCode10", "goodsName10"),
        TitleModel("goodsCode10", "goodsName10"),
        TitleModel("goodsCode10", "goodsName10"),
        TitleModel("goodsCode10", "goodsName10"),
        TitleModel("goodsCode10", "goodsName10"),
        TitleModel("goodsCode10", "goodsName10"),
        TitleModel("goodsCode10", "goodsName10"),
        TitleModel("goodsCode10", "goodsName10"),
        TitleModel("goodsCode10", "goodsName10"),
    )
    var contents : ArrayList<ContentModel> = arrayListOf(
        ContentModel(
            arrayListOf(ContentBean("content1",0),
            ContentBean("content1",0),
            ContentBean("content1",0),
            ContentBean("content1",0),
            ContentBean("content1",0),
            ContentBean("content1",0),
            ContentBean("content1",0))
            ),
        ContentModel(
            arrayListOf(ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0))
        ),
        ContentModel(
            arrayListOf(ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0))
        ),
        ContentModel(
            arrayListOf(ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0))
        ),
        ContentModel(
            arrayListOf(ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0))
        ),
        ContentModel(
            arrayListOf(ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0))
        ),
        ContentModel(
            arrayListOf(ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0))
        ),
        ContentModel(
            arrayListOf(ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0))
        ),
        ContentModel(
            arrayListOf(ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0))
        ),
        ContentModel(
            arrayListOf(ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0))
        ),
        ContentModel(
            arrayListOf(ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0))
        ),
        ContentModel(
            arrayListOf(ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0))
        ),
        ContentModel(
            arrayListOf(ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0))
        ),
        ContentModel(
            arrayListOf(ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0))
        ),
        ContentModel(
            arrayListOf(ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0))
        ),
        ContentModel(
            arrayListOf(ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0))
        ),
        ContentModel(
            arrayListOf(ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0))
        ),
        ContentModel(
            arrayListOf(ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0),
                ContentBean("content1",0))
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vhScroll_view.rightTitle = rightList
        vhScroll_view.leftTitle = leftTitle
        vhScroll_view.models = contents
        vhScroll_view.initLayout()
    }
}