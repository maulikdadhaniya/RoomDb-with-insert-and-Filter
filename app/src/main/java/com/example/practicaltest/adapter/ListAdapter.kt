package com.example.practicaltest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaltest.R
import com.example.practicaltest.localDB.product.ProductModel
import kotlinx.android.synthetic.main.row_list_layout.view.*


class ListAdapter(
    private val context: Context,
    private var items: ArrayList<ProductModel>,
    private var isCat: Boolean,
    var prevRowCat : String = ""

) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.row_list_layout,
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n", "MissingPermission")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        if (!isCat) {
            holder.txtx_cat_title.visibility = View.GONE
        } else {

                if (items[position].cat_name == prevRowCat) {
                    holder.txtx_cat_title.visibility = View.GONE
                } else {
                    holder.txtx_cat_title.visibility = View.VISIBLE
                    prevRowCat = items[position].cat_name
                }


        }

        holder.txt_name.text = "Name : " + items[position].product_name
        holder.txt_price.text = "Price : " + items[position].p_price.toString()
        holder.txt_category.text = "Category : " + items[position].cat_name
        holder.txtx_cat_title.text = items[position].cat_name


    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txt_name = view.txt_name!!
        val txt_price = view.txt_price!!
        val txt_category = view.txt_category!!
        val txtx_cat_title = view.txtx_cat_title!!


    }

}