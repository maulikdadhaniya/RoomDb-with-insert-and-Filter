package com.example.practicaltest.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.practicaltest.R
import com.example.practicaltest.adapter.ListAdapter
import com.example.practicaltest.localDB.AppDatabase
import com.example.practicaltest.localDB.product.ProductModel
import kotlinx.android.synthetic.main.activity_show_list.*
import java.util.*

class ShowListActivity : AppCompatActivity() {

    var db: AppDatabase? = null

    private var productList = ArrayList<ProductModel>()
    private var listAdapter: ListAdapter? = null
    private var isCat: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "PracticalExam")
            .allowMainThreadQueries()
            .build()


        productList = db!!.productMethods!!.getProduct() as ArrayList<ProductModel>


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_a2z -> {
                filterA2Z()
                return true
            }
            R.id.action_l2h -> {
                filterL2H()
                return true
            }
            R.id.action_cat -> {
                filterCat()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun filterA2Z() {
        isCat = false
        productList = db!!.productMethods!!.getA2ZFilter() as ArrayList<ProductModel>
        onResume()
        listAdapter!!.notifyDataSetChanged()

    }

    private fun filterL2H() {
        isCat = false
        productList = db!!.productMethods!!.getL2HFilter() as ArrayList<ProductModel>
        onResume()
        listAdapter!!.notifyDataSetChanged()

    }

    override fun onResume() {
        super.onResume()

        listAdapter = ListAdapter(
            this@ShowListActivity,
            productList,
            isCat
        )

        val mLayoutManager =
            LinearLayoutManager(
                this@ShowListActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
        rv_listData!!.layoutManager = mLayoutManager
        rv_listData!!.itemAnimator = DefaultItemAnimator()
        rv_listData!!.adapter = listAdapter

    }

    private fun filterCat() {
        isCat = true
        productList = db!!.productMethods!!.getFilterCat() as ArrayList<ProductModel>
        onResume()
        listAdapter!!.notifyDataSetChanged()


    }
}
