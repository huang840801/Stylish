package com.guanhong.stylish.util

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.Shape
import android.os.Build
import android.view.View
import android.widget.EditText
import com.guanhong.stylish.R
import com.guanhong.stylish.Stylish

fun View.show(){
    visibility = View.VISIBLE
}
fun View.hide(){
    visibility = View.GONE
}
fun View.setEdge(edgeWidth: Int, color:Int ){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        foreground = ShapeDrawable(object : Shape() {
            override fun draw(canvas: Canvas, paint: Paint) {

                paint.color = color
                paint.style = Paint.Style.STROKE
                paint.strokeWidth = Stylish.context.resources
                        .getDimensionPixelSize(edgeWidth).toFloat()
                canvas.drawRect(0f, 0f, this.width, this.height, paint)
            }
        })
    }
}
fun EditText.setEditable(canEditable : Boolean){

    if (canEditable) {
        requestFocus()
    }
    isEnabled = canEditable
    isFocusable = canEditable
    isFocusableInTouchMode = canEditable
}
fun <T : View> T.click(block: (T) -> Unit) = setOnClickListener { block(it as T) }
fun View.aaaa() : View {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
    return this
}