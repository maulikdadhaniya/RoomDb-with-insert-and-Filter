package com.example.practicaltest.localDB.category

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface CategoryMethods {
    @Insert
    fun insert(vararg addon: CategoryModel)

    @Query("SELECT * FROM category")
    fun getCategory(): List<CategoryModel>

}
