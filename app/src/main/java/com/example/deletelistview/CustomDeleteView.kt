package com.example.deletelistview

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.GestureDetector.OnGestureListener
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.ListView
import android.widget.RelativeLayout
import kotlin.math.abs


class CustomDeleteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ListView(
    context,
    attrs,
    defStyleAttr
), OnTouchListener,
    OnGestureListener {

    private val gestureDetector: GestureDetector

    private var listener: OnDeleteListener? = null

    private var deleteButton: View? = null

    private var itemLayout: ViewGroup? = null

    private var selectedItem = 0

    private var isDeleteShown = false

    interface OnDeleteListener {
        fun onDelete(index: Int)
    }

    init {
        gestureDetector = GestureDetector(context, this)
        setOnTouchListener(this)
    }

    fun setOnDeleteListener(listener: OnDeleteListener) {
        this.listener = listener
    }

    override fun onTouch(p0: View, p1: MotionEvent): Boolean {
        return if (isDeleteShown) {
            itemLayout?.removeView(deleteButton);
            deleteButton = null
            isDeleteShown = false
            false
        } else {
            gestureDetector.onTouchEvent(p1)
        }
    }

    override fun onDown(p0: MotionEvent): Boolean {
        if (!isDeleteShown) {
            selectedItem = pointToPosition(p0.x.toInt(), p0.y.toInt())
        }
        return false
    }

    override fun onShowPress(p0: MotionEvent) = Unit

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        return false
    }

    override fun onScroll(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        return false
    }

    override fun onLongPress(p0: MotionEvent) = Unit

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        if (!isDeleteShown && abs(velocityX) > abs(velocityY)) {
            deleteButton = LayoutInflater.from(context).inflate(R.layout.delete_button, null)
            deleteButton?.setOnClickListener {
                itemLayout?.removeView(it)
                deleteButton = null
                isDeleteShown = false
                listener?.onDelete(selectedItem)
            }

            itemLayout = getChildAt(selectedItem - firstVisiblePosition) as ViewGroup

            val params = RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
            params.addRule(RelativeLayout.CENTER_VERTICAL)

            itemLayout?.addView(deleteButton, params)
            isDeleteShown = true
        }
        return false
    }
}
