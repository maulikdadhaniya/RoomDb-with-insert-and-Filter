package com.example.practicaltest.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.practicaltest.R
import com.example.practicaltest.localDB.AppDatabase
import com.example.practicaltest.localDB.category.CategoryModel
import com.example.practicaltest.localDB.product.ProductModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    var db: AppDatabase? = null

    var catIdsList: ArrayList<Int> = ArrayList()

    var selectedCatId: Int = 0
    var selectedCatName: String = ""
    var catTitle: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "PracticalExam")
            .allowMainThreadQueries()
            .build()


        if (db!!.catMethods!!.getCategory().isEmpty()) {
            val cat1 = CategoryModel(
                "Cloths"
            )
            db!!.catMethods!!.insert(cat1)

            val cat2 = CategoryModel(
                "Electronics"
            )
            db!!.catMethods!!.insert(cat2)

            val cat3 = CategoryModel(
                "Beauty"
            )
            db!!.catMethods!!.insert(cat3)
        }

        var catList: ArrayList<CategoryModel> = ArrayList()

        catList.addAll(db!!.catMethods!!.getCategory())


        catList.forEach {

            catIdsList.add(it.id)
            catTitle.add(it.cat_type)

        }

        catTitle.add(0, "Select Category")
        catIdsList.add(0, 0)

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1, catTitle
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_category.adapter = arrayAdapter


        spinner_category.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                arg0: AdapterView<*>?,
                arg1: View?,
                position: Int,
                id: Long
            ) {

                if (position == 0) {

                } else {
                    selectedCatId = catIdsList[position]
                    selectedCatName = catTitle[position]
                }
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {

            }
        }

        btn_save.setOnClickListener {

            if (edt_productName.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter ProductName", Toast.LENGTH_SHORT).show()
            } else if (edt_amount.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter Price", Toast.LENGTH_SHORT).show()
            } else if (selectedCatId == 0) {
                Toast.makeText(this, "Please select category", Toast.LENGTH_SHORT).show()
            } else {

                val dataCollection = ProductModel(
                    selectedCatId,
                    selectedCatName,
                    edt_productName.text.toString(),
                    edt_amount.text.toString().toDouble()
                )
                db!!.productMethods!!.insertProduct(dataCollection)
                Toast.makeText(this, "Product Added", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, ShowListActivity::class.java)
                startActivity(intent)

            }
        }

    }


}
