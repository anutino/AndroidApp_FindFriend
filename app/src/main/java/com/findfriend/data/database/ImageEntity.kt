package com.findfriend.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image")
data class ImageEntity(@PrimaryKey(autoGenerate = true)
                       val id: Int, val url: String,
                       @ColumnInfo(name = "animal_id") val animalId: Int
)