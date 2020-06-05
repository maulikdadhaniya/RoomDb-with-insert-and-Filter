package com.example.practicaltest.localDB.product

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ProductMethods {
    @Insert
    fun insertProduct(vararg addon: ProductModel)

    @Query("SELECT * FROM product")
    fun getProduct(): List<ProductModel>

    @Query("SELECT * FROM product ORDER BY p_name COLLATE NOCASE ASC")
    fun getA2ZFilter(): List<ProductModel>

    @Query("SELECT * FROM product ORDER BY p_price ASC")
    fun getL2HFilter(): List<ProductModel>

    @Query("SELECT * FROM product ORDER BY cat_name COLLATE NOCASE ASC")
    fun getFilterCat(): List<ProductModel>

}
