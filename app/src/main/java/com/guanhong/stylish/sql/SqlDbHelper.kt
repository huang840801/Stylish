package com.guanhong.stylish.sql

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.guanhong.stylish.model.CartProduct
import com.guanhong.stylish.model.Product
import com.guanhong.stylish.model.Variant

class SqlDbHelper(private val context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {

    private lateinit var cursor: Cursor

    companion object {
        const val DATABASE_NAME = "stylish.db"
        const val VERSION = 1

        const val TABLE_NAME = "stylish_product"

        const val PRODUCT_ID = "id"
        const val PRODUCT_TITLE = "title"
        const val PRODUCT_DESCRIPTION = "description"
        const val PRODUCT_PRICE = "price"
        const val PRODUCT_TEXTURE = "texture"
        const val PRODUCT_WASH = "wash"
        const val PRODUCT_PLACE = "place"
        const val PRODUCT_NOTE = "note"
        const val PRODUCT_STORY = "story"
        const val PRODUCT_STOCK = "stock"
        const val PRODUCT_MAIN_IMAGE = "main_image"
        const val COLORS = "colors"
        const val COLOR_NAME = "name"
        const val COLOR_CODE = "code"
        const val SIZES = "sizes"
        const val VARIANTS = "variants"
        const val VARIANT_COLOR_CODE = "selected_color_code"
        const val VARIANT_SIZE = "selected_size"
        const val VARIANT_STOCK = "selected_stock"
        const val IMAGES = "images"
        const val CREATE_CART_TABLE = ("CREATE TABLE " + TABLE_NAME + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PRODUCT_ID + " TEXT NOT NULL, "
                + PRODUCT_TITLE + " TEXT NOT NULL, "
                + PRODUCT_DESCRIPTION + " TEXT NOT NULL, "
                + PRODUCT_PRICE + " INTEGER NOT NULL, "
                + PRODUCT_TEXTURE + " TEXT NOT NULL, "
                + PRODUCT_WASH + " TEXT NOT NULL, "
                + PRODUCT_PLACE + " TEXT NOT NULL, "
                + PRODUCT_NOTE + " TEXT NOT NULL, "
                + PRODUCT_STORY + " TEXT NOT NULL, "
                + PRODUCT_STOCK + " INTEGER NOT NULL, "
                + PRODUCT_MAIN_IMAGE + " TEXT NOT NULL, "
//                + COLORS + " TEXT NOT NULL, "
                + COLOR_CODE + " TEXT NOT NULL, "
                + COLOR_NAME + " TEXT NOT NULL, "
//                + SIZES + " TEXT NOT NULL, "
//                + VARIANTS + " TEXT NOT NULL, "
//                + IMAGES + " TEXT NOT NULL, "
                + VARIANT_COLOR_CODE + " TEXT NOT NULL, "
                + VARIANT_SIZE + " TEXT NOT NULL, "
                + VARIANT_STOCK + " TEXT NOT NULL)")

        fun getDatabase(context: Context): SqlDbHelper {
            return SqlDbHelper(context)
        }
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(CREATE_CART_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        onCreate(db)
    }

    fun insert(cartProduct: CartProduct): Boolean {

//        Log.d("Huang", " id = " + cartProduct.id)
//        Log.d("Huang", " title = " + cartProduct.title)
//        Log.d("Huang", " description = " + cartProduct.description)
//        Log.d("Huang", " price = " + cartProduct.price)
//        Log.d("Huang", " texture = " + cartProduct.texture)
//        Log.d("Huang", " wash = " + cartProduct.wash)
//        Log.d("Huang", " place = " + cartProduct.place)
//        Log.d("Huang", " note = " + cartProduct.note)
//        Log.d("Huang", " story " + cartProduct.story)
        Log.d("Huang", " stock " + cartProduct.stock)
//        Log.d("Huang", " main_image = " + cartProduct.mainImage)
        Log.d("Huang", " color_code = " + cartProduct.selectedColorCode)
        Log.d("Huang", " size = " + cartProduct.selectedSize)
        Log.d("Huang", " selected stock = " + cartProduct.selectedStock)

        val db = SqlDbHelper(context).writableDatabase
        val contentValue = ContentValues()
        contentValue.put(PRODUCT_ID, cartProduct.id)
        contentValue.put(PRODUCT_TITLE, cartProduct.title)
        contentValue.put(PRODUCT_DESCRIPTION, cartProduct.description)
        contentValue.put(PRODUCT_PRICE, cartProduct.price)
        contentValue.put(PRODUCT_TEXTURE, cartProduct.texture)
        contentValue.put(PRODUCT_WASH, cartProduct.wash)
        contentValue.put(PRODUCT_PLACE, cartProduct.place)
        contentValue.put(PRODUCT_NOTE, cartProduct.note)
        contentValue.put(PRODUCT_STORY, cartProduct.story)
        contentValue.put(PRODUCT_STOCK, cartProduct.stock)
        contentValue.put(PRODUCT_MAIN_IMAGE, cartProduct.mainImage)
        contentValue.put(PRODUCT_STORY, cartProduct.story)
        contentValue.put(COLOR_CODE, cartProduct.colorCode)
        contentValue.put(COLOR_NAME, cartProduct.colorName)

//        contentValue.put(COLORS, cartProduct.id)
//        contentValue.put(SIZES, cartProduct.id)
//        contentValue.put(IMAGES, cartProduct.images)
        contentValue.put(VARIANT_COLOR_CODE, cartProduct.selectedColorCode)
        contentValue.put(VARIANT_SIZE, cartProduct.selectedSize)
        contentValue.put(VARIANT_STOCK, cartProduct.selectedStock
        )
        val row = db.insert(TABLE_NAME, null, contentValue).toInt()

        return row != -1
    }

    fun getCartProduct(context: Context): List<CartProduct> {

        val db = SqlDbHelper(context).readableDatabase

        cursor = db.query(TABLE_NAME,
                arrayOf(
                        PRODUCT_ID,
                        PRODUCT_TITLE,
                        PRODUCT_DESCRIPTION,
                        PRODUCT_PRICE,
                        PRODUCT_TEXTURE,
                        PRODUCT_WASH,
                        PRODUCT_PLACE,
                        PRODUCT_NOTE,
                        PRODUCT_STORY,
                        PRODUCT_STOCK,
                        PRODUCT_MAIN_IMAGE,
                        VARIANT_COLOR_CODE,
                        VARIANT_SIZE,
                        VARIANT_STOCK),
                null, null, null, null, null
        )

        val cartProducts: MutableList<CartProduct> = mutableListOf()

        while (cursor.moveToNext()) {
            val cartProduct = CartProduct()
            cartProduct.id = cursor.getString(cursor.getColumnIndex(PRODUCT_ID))
            cartProduct.title = cursor.getString(cursor.getColumnIndex(PRODUCT_TITLE))
            cartProduct.description = cursor.getString(cursor.getColumnIndex(PRODUCT_DESCRIPTION))
            cartProduct.price = cursor.getInt(cursor.getColumnIndex(PRODUCT_PRICE))
            cartProduct.texture = cursor.getString(cursor.getColumnIndex(PRODUCT_TEXTURE))
            cartProduct.wash = cursor.getString(cursor.getColumnIndex(PRODUCT_WASH))
            cartProduct.place = cursor.getString(cursor.getColumnIndex(PRODUCT_PLACE))
            cartProduct.note = cursor.getString(cursor.getColumnIndex(PRODUCT_NOTE))
            cartProduct.story = cursor.getString(cursor.getColumnIndex(PRODUCT_STORY))
            cartProduct.stock = cursor.getInt(cursor.getColumnIndex(PRODUCT_STOCK))
            cartProduct.mainImage = cursor.getString(cursor.getColumnIndex(PRODUCT_MAIN_IMAGE))
            cartProduct.selectedColorCode = cursor.getString(cursor.getColumnIndex(VARIANT_COLOR_CODE))
            cartProduct.selectedSize = cursor.getString(cursor.getColumnIndex(VARIANT_SIZE))
            cartProduct.selectedStock = cursor.getInt(cursor.getColumnIndex(VARIANT_STOCK))

            cartProducts.add(cartProduct)
        }

        return cartProducts
    }

    fun delete(id: String): Int {

        val where = PRODUCT_ID + "=" + id
        val db = SqlDbHelper(context).writableDatabase

        return db.delete(TABLE_NAME, where, null)
    }
}