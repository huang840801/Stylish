package com.guanhong.stylish.ui.add2cart

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guanhong.stylish.R
import com.guanhong.stylish.model.Product
import com.guanhong.stylish.util.hide
import com.guanhong.stylish.util.setEdge
import com.guanhong.stylish.util.show
import kotlinx.android.synthetic.main.dialog_add_cart.*
import kotlinx.android.synthetic.main.item_add_cart_color.view.*


class AddCartFragment : BottomSheetDialogFragment() {

    private lateinit var product: Product

    private var colorViewList = mutableListOf<View>()
    private var colorCodeList = mutableListOf<String>()
    private var selectColor: String = ""
    private var selectSize: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NO_TITLE, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_add_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            product = arguments!!.getSerializable("product") as Product
        }

        countEditText.isClickable = false
        countEditText.isFocusable = false

        setProductDetail(product)

        addToCart.setOnClickListener {
            addToCart()
        }
    }

    private fun addToCart() {

        Log.d("Huang", " selectColor = $selectColor")
        Log.d("Huang", " selectSize = $selectSize")
        Log.d("Huang", " selectCount = ${getCountEditTextInt()}")
    }

    private fun setProductDetail(product: Product) {
        title.text = product.title
        price.text = "NT $ " + product.price.toString()

        val gd = GradientDrawable(
                GradientDrawable.Orientation.BOTTOM_TOP,
                intArrayOf(Color.parseColor("#ffffff"), Color.parseColor("#ffffff")))
        gd.setStroke(2, resources.getColor(R.color.light_gray))
        countEditText.setBackgroundDrawable(gd)

        addButton.setEdge(
                R.dimen.edge_add2cart_select_2dp,
                resources.getColor(R.color.light_gray))
        subtractButton.setEdge(
                R.dimen.edge_add2cart_select_2dp,
                resources.getColor(R.color.light_gray))

        setColor()
    }

    private fun setColor() {

        colorLayout.removeAllViews()
        product.colors.forEach {

            val code = it.code
            val colorChildView = View.inflate(context, R.layout.item_add_cart_color, null)
            colorViewList.add(colorChildView.childView)
            colorCodeList.add(it.code)
            colorChildView.setOnClickListener {

                stockCount.hide()
                stockText.hide()

                setSize(code)
                selectColor = code
                setColorEdge(code)
            }

            colorChildView.childView.setBackgroundColor(Color.parseColor("#" + it.code))
            colorChildView.childView.setEdge(
                    R.dimen.edge_add2cart_select_2dp,
                    R.color.black)
            colorLayout.addView(colorChildView)
        }
    }

    private fun setColorEdge(code: String) {

        Log.d("Huang  ", " aaa = $code")
        Log.d("Huang  ", " count = "+ colorViewList.count())


        colorViewList.forEachIndexed { index, view ->

            view.setEdge( R.dimen.edge_add2cart_select_0dp, resources.getColor(R.color.black))
            if (index == colorCodeList.indexOf(code)){
                view.setEdge( R.dimen.edge_add2cart_select_10dp, resources.getColor(R.color.black))

            }
        }
    }


    private fun setSize(code: String) {

        sizeLayout.removeAllViews()
        product.variants.filter { it.color_code == code }.forEach { variant ->

            val count = variant.stock
            val itemView = View.inflate(context, R.layout.item_add_cart_color, null)
            itemView.setOnClickListener {
                setStockCount(count)

                selectSize = variant.size
                itemView.childView.setEdge(
                        R.dimen.edge_add2cart_select_10dp,
                        resources.getColor(R.color.black))
            }

            itemView.childView.text = variant.size
            sizeLayout.addView(itemView)
        }

    }

    private fun setStockCount(count: Int) {
        stockCount.text = count.toString()
        stockCount.show()
        stockText.show()
        countEditText.setText("1")

        subtractButton.setOnClickListener {
            if (getCountEditTextInt() > 1) {
                countEditText.setText((getCountEditTextInt() - 1).toString())
            }
        }
        addButton.setOnClickListener {
            if (getCountEditTextInt() < count) {
                countEditText.setText((getCountEditTextInt() + 1).toString())
            }
        }
    }

    private fun getCountEditTextInt(): Int {
        return countEditText.text.toString().toInt()
    }
}