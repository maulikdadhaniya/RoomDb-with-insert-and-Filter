package com.example.practicaltest.localDB.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "category")
class CategoryModel(
    @field:ColumnInfo(name = "cat_type") var cat_type: String
) :
    Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}