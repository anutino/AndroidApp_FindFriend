package com.findfriend.data.database

import androidx.room.*
import com.findfriend.domain.model.ShortAnimalInfo
import io.reactivex.rxjava3.core.Single

@Dao
interface AnimalDao {
    @Query("SELECT Animal.id, Animal.age, Animal.name, Animal.type, Animal.mainPicture, Animal.favorite FROM Animal " +
            "LEFT JOIN Image ON Image.id = Animal.mainPicture ")
    fun getAnimalShortInfoList(): Single<List<ShortAnimalInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnimal(animal: AnimalEntity)

}
