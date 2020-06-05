package com.example.practicaltest.localDB.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "product")
class ProductModel(
    @field:ColumnInfo(name = "cat_id") var cat_id: Int,
    @field:ColumnInfo(name = "cat_name") var cat_name: String,
    @field:ColumnInfo(name = "p_name") var product_name: String,
    @field:ColumnInfo(name = "p_price") var p_price: Double
) :
    Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}