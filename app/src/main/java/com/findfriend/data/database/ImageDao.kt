package com.findfriend.data.database

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface ImageDao {

    @Insert(entity = ImageEntity::class)
    fun insertImage(image: ImageEntity)
}