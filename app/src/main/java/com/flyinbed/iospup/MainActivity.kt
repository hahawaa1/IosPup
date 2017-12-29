package com.flyinbed.iospup

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.flyinbed.iapopup.SelectPicPopupWindow
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        text.setOnClickListener {
           var popup =  SelectPicPopupWindow(this)
//            popup.topView.text = "我是顶部"
            popup.topView.setOnClickListener {
                println("aaaaaa")
                popup.hint()
            }
            popup.show(main)
        }

        text1.setOnClickListener {
            var popup =  SelectPicPopupWindow(this)
//            popup.topView.text = "我是顶部"
            popup.fristView.setOnClickListener {
                println("aaaaaa")
                popup.hint()
            }
            popup.show(main)
        }
    }
}
