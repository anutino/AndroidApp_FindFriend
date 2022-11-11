package com.findfriend.data.testdatastorage

import android.content.Context
import com.findfriend.app.R
import com.findfriend.data.Constants
import com.findfriend.data.database.AnimalEntity
import com.findfriend.data.database.ImageEntity
import com.findfriend.domain.model.AnimalDetailedInfo
import com.findfriend.domain.model.AnimalType
import com.findfriend.domain.model.ShortAnimalInfo
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestAnimalData @Inject constructor(val context: Context) {

    fun getAnimalTypes(): Single<List<AnimalType>> {
        val dog = AnimalType(0, "DOG", "dog1.jpg")
        val cat = AnimalType(1, "CAT", "cat1.jpg")
        val all = AnimalType(2, "ALL", "all.jpg")
        return Single.just(listOf(dog, cat, all))
    }

    fun getAnimalShortInfoList(): Single<List<ShortAnimalInfo>> {
        val list = mutableListOf<ShortAnimalInfo>()
        list.add(0, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog1.jpg", false))
        list.add(1, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog2.jpg", true))
        list.add(2, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog3.jpg", true))
        list.add(3, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog4.jpg", true))
        list.add(4, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog5.jpg", true))
        list.add(5, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog6.jpg", true))
        list.add(6, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog7.jpg", true))
        list.add(7, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog8.jpg", true))
        list.add(8, ShortAnimalInfo(0, 1.0, "Iris", "2", "cat1.jpg", true))
        list.add(9, ShortAnimalInfo(0, 1.0, "Iris", "2", "cat2.jpg", true))
        list.add(10, ShortAnimalInfo(0, 1.0, "Iris", "2", "cat3.jpg", true))
        return Single.just(list)
    }
    fun getFavoriteAnimalList(){
        val list = mutableListOf<ShortAnimalInfo>()
        list.add(0, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog1.jpg", false))
        list.add(1, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog2.jpg", true))
        list.add(2, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog3.jpg", true))
        list.add(3, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog4.jpg", true))
        list.add(4, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog5.jpg", true))
        list.add(5, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog6.jpg", true))
        list.add(6, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog7.jpg", true))
        list.add(7, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog8.jpg", true))
        list.add(8, ShortAnimalInfo(0, 1.0, "Iris",  "2", "cat1.jpg", true))
        list.add(9, ShortAnimalInfo(0, 1.0, "Iris",  "2", "cat2.jpg", true))
        list.add(10, ShortAnimalInfo(0, 1.0, "Iris",  "2", "cat3.jpg", true))
    }

    fun getAnimalShortInfoListFilteredByAge(minAge: Int,
                                            maxAge: Int
    ): Single<List<ShortAnimalInfo>> {
        val list = mutableListOf<ShortAnimalInfo>()
        list.add(0, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog1.jpg", false))
        list.add(1, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog2.jpg", true))
        list.add(2, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog3.jpg", true))
        list.add(3, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog4.jpg", true))
        list.add(4, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog5.jpg", true))
        list.add(5, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog6.jpg", true))
        list.add(6, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog7.jpg", true))
        list.add(7, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog8.jpg", true))
        return Single.just(list)
    }

    fun getAnimalShortInfoListFilteredByType(): Single<List<ShortAnimalInfo>> {
        val list = mutableListOf<ShortAnimalInfo>()
        list.add(0, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog1.jpg", false))
        list.add(1, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog2.jpg", true))
        list.add(2, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog3.jpg", true))
        list.add(3, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog4.jpg", true))
        list.add(4, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog5.jpg", true))
        list.add(5, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog6.jpg", true))
        list.add(6, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog7.jpg", true))
        list.add(7, ShortAnimalInfo(0, 1.0, "Iris", "2", "dog8.jpg", true))
        return Single.just(list)
    }

    fun getDetailedInfo(): Single<AnimalDetailedInfo> {
        return Single.just(AnimalDetailedInfo(0,
            1.0,
            "Bambi",
            context.getString(R.string.long_text_example),
            Constants.AnimalType.DOG.ordinal,
            listOf(R.drawable.bambi_1, R.drawable.bambi_2, R.drawable.bambi_3, R.drawable.bambi_4),
            false))
    }

    fun getImages(): ImageEntity {
        return ImageEntity(1, "bambi_1", 1)
    }

    fun getAnimal(): AnimalEntity {
        return AnimalEntity(1, 2, "Bambi",
            "A very good dog", 1, 1, false)
    }

}