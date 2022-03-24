package com.findfriend.data.repository

import android.util.Log
import androidx.annotation.Nullable
import com.findfriend.domain.model.Animal
import com.findfriend.domain.model.AnimalDetailedInfo
import com.findfriend.domain.model.AnimalType
import com.findfriend.domain.model.ShortAnimalInfo
import com.findfriend.data.networkservice.NetworkService
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimalRepositoryImpl @Inject constructor(networkService: NetworkService) : AnimalRepository {

    companion object {
        private val TAG = javaClass.simpleName
    }

    private val mNetworkService = networkService
    private var mSelectedItem: Int = 0
    private var mAgeFrom: Int = 0
    private var mAgeTo: Int = 0
    private var mType: Int = 0

    override fun fetchAnimalShortInfoList(): List<ShortAnimalInfo> {
        var result = mutableListOf<ShortAnimalInfo>()
        mNetworkService.getInstance()
            .fetchAnimalShortInfoList()
            .enqueue(object : Callback<List<ShortAnimalInfo>> {
                override fun onResponse(
                    call: Call<List<ShortAnimalInfo>>,
                    response: Response<List<ShortAnimalInfo>>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            try {
                                result = (response.body()!!.toMutableList())
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        }
                    } else {
                        Log.d(TAG, "not success");
                    }
                }

                override fun onFailure(call: Call<List<ShortAnimalInfo>>, t: Throwable) {
                    Log.e(TAG, "onFailure");
                }
            })
        return result
    }

    override fun fetchAnimalShortInfoListFilteredByType(type: Int
    ): List<ShortAnimalInfo> {
        var result = mutableListOf<ShortAnimalInfo>()
        mNetworkService.getInstance()
            .fetchAnimalShortInfoListFilteredByType(type)
            .enqueue(object : Callback<List<ShortAnimalInfo>> {
                override fun onResponse(
                    call: Call<List<ShortAnimalInfo>>,
                    response: Response<List<ShortAnimalInfo>>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            try {
                                result = (response.body()!!.toMutableList())
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        }
                    } else {
                        Log.d(TAG, "not success");
                    }
                }

                override fun onFailure(call: Call<List<ShortAnimalInfo>>, t: Throwable) {
                    Log.d(TAG, "onFailure");
                }
            })
        return result
    }

    override fun fetchAnimalsShortInfoListFilteredByAgeAndType(
        minAge: String,
        maxAge: String,
        type: Int
    ): List<ShortAnimalInfo> {
        var result = mutableListOf<ShortAnimalInfo>()
        mNetworkService.getInstance()
            .fetchAnimalShortInfoListFilteredByAgeAndType(minAge, maxAge, type)
            .enqueue(object : Callback<List<ShortAnimalInfo>> {
                override fun onResponse(
                    call: Call<List<ShortAnimalInfo>>,
                    response: Response<List<ShortAnimalInfo>>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            try {
                                result = (response.body()!!.toMutableList())
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        }
                    } else {
                        Log.d(TAG, "not success");
                    }
                }

                override fun onFailure(call: Call<List<ShortAnimalInfo>>, t: Throwable) {
                    Log.d(TAG, "onFailure");
                }
            })
        return result
    }


    override fun fetchAnimalShortListFilteredByAge(minAge: String, maxAge: String
    ): List<ShortAnimalInfo> {
        var result = mutableListOf<ShortAnimalInfo>()
        mNetworkService.getInstance()
            .fetchAnimalShortInfoListFilteredByAge(minAge, maxAge)
            .enqueue(object : Callback<List<ShortAnimalInfo>> {
                override fun onResponse(
                    call: Call<List<ShortAnimalInfo>>,
                    response: Response<List<ShortAnimalInfo>>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            try {
                                result = (response.body()!!.toMutableList())
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        }
                    } else {
                        Log.d(TAG, "not success");
                    }
                }

                override fun onFailure(call: Call<List<ShortAnimalInfo>>, t: Throwable) {
                    Log.d(TAG, "onFailure");
                }
            })
        return result
    }

    @Nullable
    override fun fetchAnimalDetailedInfo(
        animalId: Int
    ): AnimalDetailedInfo {
        var mMedia: List<String> = listOf("Example", "Program", "Tutorial")
        var result = AnimalDetailedInfo(0, 0.0, "", "", 0, mMedia, false)
        mNetworkService.getInstance()
            .fetchDetailedInfo(animalId)
            .enqueue(object : Callback<AnimalDetailedInfo> {
                override fun onResponse(
                    call: Call<AnimalDetailedInfo>,
                    response: Response<AnimalDetailedInfo>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            try {
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        }
                    } else {
                        Log.d(TAG, "not success");
                    }
                }

                override fun onFailure(call: Call<AnimalDetailedInfo>, t: Throwable) {
                    Log.d(TAG, "onFailure");
                }
            })
        return result
    }

    override fun fetchFavoritesAnimals(animalId: String
    ): List<ShortAnimalInfo> {
        var result = mutableListOf<ShortAnimalInfo>()
        mNetworkService.getInstance()
            .fetchFavoriteAnimalList(animalId)
            .enqueue(object : Callback<List<ShortAnimalInfo>> {
                override fun onResponse(
                    call: Call<List<ShortAnimalInfo>>,
                    response: Response<List<ShortAnimalInfo>>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            try {
                                result = (response.body()!!.toMutableList())
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        }
                    } else {
                        Log.d(TAG, "not success");
                    }
                }

                override fun onFailure(call: Call<List<ShortAnimalInfo>>, t: Throwable) {
                    Log.d(TAG, "onFailure");
                }
            })
        return result
    }

    override fun fetchAnimalTypes(): List<AnimalType> {
        var result = mutableListOf<AnimalType>()
        mNetworkService.getInstance()
            .fetchAnimalTypes()
            .enqueue(object : Callback<List<AnimalType>> {
                override fun onResponse(
                    call: Call<List<AnimalType>>,
                    response: Response<List<AnimalType>>
                ) {
                    if (response.isSuccessful) {
                        print("Request Successful")
                        if (response.body() != null) {
                            try {
                                var json = JSONObject(response.body().toString())
                                json.getJSONArray("categoryList")
                                val jsonArray = JSONArray(json)
                                print("res " + jsonArray)
                                for (i in 0 until jsonArray.length()) {
                                    val tt: JSONObject = jsonArray.getJSONObject(i)
                                    var t = AnimalType(
                                        tt.getInt("id"),
                                        tt.getString("name"),
                                        tt.getString("img_url")
                                    )
                                }
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        }
                    } else {
                        print("not success")
                    }
                }

                override fun onFailure(call: Call<List<AnimalType>>, t: Throwable) {
                    print("onFailure")
                }
            })
        return result
    }

    override fun fetchAnimalInfoById(): List<Animal> {
        var result = mutableListOf<Animal>()
        return result
    }

    override fun setItemIdSelected(id: Int) {
        mSelectedItem = id
    }

    override fun getItemIdSelected(): Int {
        return mSelectedItem
    }

    override fun setAge(from: Int, to: Int) {
        mAgeFrom = from
        mAgeTo = to
    }

    override fun getAgeFrom(): Int {
        return mAgeFrom
    }

    override fun getAgeTo(): Int {
        return mAgeTo
    }

    override fun setType(type: Int) {
        mType = type
    }

    override fun getType(): Int {
        return mType
    }
}