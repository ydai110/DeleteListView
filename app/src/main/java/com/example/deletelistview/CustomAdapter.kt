package com.example.deletelistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

class CustomAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int,
    @IdRes private val textViewResourceId: Int = 0,
    private val values: List<String>
) : ArrayAdapter<String>(context, layoutResource, values) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_view, null)

        val textView: TextView = view.findViewById(R.id.text_view)
        textView.text = getItem(position)
        return view
    }
}
