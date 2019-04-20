package com.guanhong.stylish.ui.catalog.child

import android.app.Activity
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guanhong.stylish.BaseFragment
import com.guanhong.stylish.R
import com.guanhong.stylish.Stylish
import com.guanhong.stylish.model.Product
import com.guanhong.stylish.util.ApiConfig.Companion.ACCESSORIES
import com.guanhong.stylish.util.ApiConfig.Companion.MEN
import com.guanhong.stylish.util.ApiConfig.Companion.WOMEN
import com.guanhong.stylish.util.hide
import com.guanhong.stylish.util.show
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_catalog_child.*
import javax.inject.Inject

class CatalogChildFragment : BaseFragment(), CatalogChildContract.View {

    @Inject
    lateinit var presenter: CatalogChildPresenter

    internal lateinit var itemType: TYPE
    private lateinit var params: String
    private lateinit var adapter: CatalogChildAdapter

    override fun onAttach(activity: Activity?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_catalog_child, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CatalogChildAdapter()
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(SpacesItemDecoration(Stylish.context.resources.getDimensionPixelSize(R.dimen.item_decoration)))

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1)) {
                    presenter.getProductList(params)
                }
            }
        })

        params = when (itemType) {
            TYPE.WOMEN -> {
                WOMEN
            }
            TYPE.MEN -> {
                MEN
            }
            TYPE.ACCESSORIES -> {
                ACCESSORIES
            }
        }
        presenter.getProductList(params)
    }

    override fun onBindProductList(productList: List<Product>) {

        activity!!.runOnUiThread {
            adapter.setProductList(productList)
            progressBar.hide()
        }
    }

    override fun showProgressBar() {
        progressBar.show()
    }

    fun newInstance(): CatalogChildFragment {
        return CatalogChildFragment()
    }
}

class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)

        outRect.right = space
        if (position % 2 == 0) {

            outRect.left = space
        }
    }
}

enum class TYPE { WOMEN, MEN, ACCESSORIES }
