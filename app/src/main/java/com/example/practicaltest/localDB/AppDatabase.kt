package com.example.practicaltest.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practicaltest.localDB.category.CategoryMethods
import com.example.practicaltest.localDB.category.CategoryModel
import com.example.practicaltest.localDB.product.ProductMethods
import com.example.practicaltest.localDB.product.ProductModel


@Database(entities = [CategoryModel::class, ProductModel::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val catMethods: CategoryMethods?
    abstract val productMethods: ProductMethods?
}