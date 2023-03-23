package com.example.deletelistview

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val contentList: MutableList<String> = mutableListOf()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initList()

        val listView = findViewById<CustomDeleteView>(R.id.my_list_view)
        val customAdapter = CustomAdapter(this, 0, 0, contentList)
        listView.setOnDeleteListener(object : CustomDeleteView.OnDeleteListener {
            override fun onDelete(index: Int) {
                contentList.removeAt(index)
                customAdapter.notifyDataSetChanged()
            }
        })

        listView.adapter = customAdapter

    }

    private fun initList() {
        contentList.add("Content Item 1")
        contentList.add("Content Item 2")
        contentList.add("Content Item 3")
        contentList.add("Content Item 4")
        contentList.add("Content Item 5")
        contentList.add("Content Item 6")
        contentList.add("Content Item 7")
        contentList.add("Content Item 8")
        contentList.add("Content Item 9")
        contentList.add("Content Item 10")
        contentList.add("Content Item 11")
        contentList.add("Content Item 12")
        contentList.add("Content Item 13")
        contentList.add("Content Item 14")
        contentList.add("Content Item 15")
        contentList.add("Content Item 16")
        contentList.add("Content Item 17")
        contentList.add("Content Item 18")
        contentList.add("Content Item 19")
        contentList.add("Content Item 20")
    }
}