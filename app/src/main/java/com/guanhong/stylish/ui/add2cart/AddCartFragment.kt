package com.guanhong.stylish.ui.add2cart

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.guanhong.stylish.R
import com.guanhong.stylish.model.Product
import com.guanhong.stylish.util.hide
import com.guanhong.stylish.util.setEdge
import com.guanhong.stylish.util.setEditable
import com.guanhong.stylish.util.show
import kotlinx.android.synthetic.main.dialog_add_cart.*
import kotlinx.android.synthetic.main.item_add_cart_color.view.*
import android.content.Context.INPUT_METHOD_SERVICE
import com.guanhong.stylish.model.CartProduct
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class AddCartFragment : BottomSheetDialogFragment(), AddCartContract.View {

    @Inject
    lateinit var presenter: AddCartPresenter

    private lateinit var product: Product
    private lateinit var listener: AddCartFragmentListener

    private var colorViewList = mutableListOf<View>()
    private var colorCodeList = mutableListOf<String>()
    private var sizeViewList = mutableListOf<View>()
    private var sizeCodeList = mutableListOf<String>()
    private var stock = 0
    private var selectColor = ""
    private var selectSize = ""

    interface AddCartFragmentListener {
        fun addToCart(isSuccess: Boolean)
    }

    override fun onAttach(activity: Activity?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(activity)
    }

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

        colorViewList.clear()
        colorCodeList.clear()
        sizeViewList.clear()
        sizeCodeList.clear()

        countEditText.setEditable(false)
        countEditText.setText("1")

        setProductDetail()

        addToCart.setOnClickListener {
            addToCart()
        }
        closeButton.setOnClickListener {
            this.dismiss()
        }
        bottomSheetLayout.setOnClickListener {
            hideKeyBroad()
        }
    }

    override fun showAddToCartResult(isSuccess: Boolean) {
        this.dismiss()
        listener.addToCart(isSuccess)
    }

    fun setListener(listener: AddCartFragmentListener) {
        this.listener = listener
    }

    private fun addToCart() {

        val cartProduct = CartProduct()

        cartProduct.id = this.product.id
        cartProduct.title = this.product.title
        cartProduct.description = this.product.description
        cartProduct.price = this.product.price
        cartProduct.texture = this.product.texture
        cartProduct.wash = this.product.wash
        cartProduct.place = this.product.place
        cartProduct.note = this.product.note
        cartProduct.story = this.product.story
        cartProduct.stock = this.stock
        cartProduct.mainImage = this.product.main_image
        cartProduct.selectedColorCode = selectColor
        cartProduct.selectedSize = selectSize
        cartProduct.selectedStock = getCountEditTextInt()

        presenter.addToCart(this.context!!, cartProduct)
    }

    private fun setProductDetail() {
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
                countEditText.setEditable(false)

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

    private fun setSize(code: String) {

        sizeLayout.removeAllViews()
        product.variants.filter { it.color_code == code }.forEach { variant ->

            if (variant.stock != 0) {

                val count = variant.stock
                val itemView = View.inflate(context, R.layout.item_add_cart_color, null)
                sizeViewList.add(itemView.childView)
                sizeCodeList.add(variant.size)
                itemView.setOnClickListener {
                    this.stock = count
                    setStockCount(count)

                    countEditText.setEditable(true)

                    countEditText.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {}
                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                        override fun onTextChanged(number: CharSequence?, start: Int, before: Int, count: Int) {
                            if (number!!.isNotEmpty() && number.toString().toInt() > variant.stock) {

                                stockText.setTextColor(resources.getColor(R.color.red))
                                stockCount.setTextColor(resources.getColor(R.color.red))
                            } else {
                                stockText.setTextColor(resources.getColor(R.color.black))
                                stockCount.setTextColor(resources.getColor(R.color.black))
                            }
                        }

                    })
                    selectSize = variant.size

                    setSizeEdge(code)
                    itemView.childView.setEdge(
                            R.dimen.edge_add2cart_select_10dp,
                            resources.getColor(R.color.black))
                }
                itemView.childView.text = variant.size
                sizeLayout.addView(itemView)
            }
        }
    }

    private fun setColorEdge(code: String) {

        colorViewList.forEachIndexed { index, view ->

            if (index == colorCodeList.indexOf(code)) {
                view.setEdge(R.dimen.edge_add2cart_select_10dp, resources.getColor(R.color.black))

            } else {
                view.setEdge(
                        R.dimen.edge_add2cart_select_2dp,
                        R.color.black)
            }
        }
    }

    private fun setSizeEdge(code: String) {
        sizeViewList.forEachIndexed { index, view ->

            view.setEdge(R.dimen.edge_add2cart_select_0dp, resources.getColor(R.color.black))
            if (index == sizeCodeList.indexOf(code)) {
                view.setEdge(R.dimen.edge_add2cart_select_10dp, resources.getColor(R.color.black))
            }
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

    private fun hideKeyBroad() {
        val imm = context!!.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }
}