package com.findfriend.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.findfriend.networkservice.NetworkService
import com.findfriend.repository.AnimalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimalRepositoryImpl(networkService: NetworkService) : AnimalRepository {

    private val TAG = javaClass.simpleName

    private val mNetworkService = networkService
//    companion object {
//        var mNetworkService: NetworkService =
//            NetworkService()
//    }

    override fun fetchAnimalShortInfoList(animalInfoLiveData: MutableLiveData<List<ShortAnimalInfo>>) { //TODO its coorect postValue call ????
        Log.d(TAG, "onFetchAnimalShortInfoList ");
        GlobalScope.launch(Dispatchers.IO) {
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
                                    animalInfoLiveData.postValue(response.body()!!.toMutableList())
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
        }
    }

    override fun fetchAnimalShortInfoListFilteredByType(
        animalInfoLiveData: MutableLiveData<List<ShortAnimalInfo>>, type: Int
    ) {
        GlobalScope.launch(Dispatchers.IO) {
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
                                    animalInfoLiveData.postValue(response.body()!!.toMutableList())
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
        }
    }

    override fun fetchAnimalsShortInfoListFilteredByAgeAndType(
        animalInfoLiveData: MutableLiveData<List<ShortAnimalInfo>>,
        minAge: String,
        maxAge: String,
        type: Int
    ) {
        GlobalScope.launch(Dispatchers.IO) {
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
                                    animalInfoLiveData.postValue(response.body()!!.toMutableList())
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
        }
    }


    override fun fetchAnimalShortListFilteredByAge(
        animalInfoLiveData: MutableLiveData<List<ShortAnimalInfo>>, minAge: String, maxAge: String
    ) {
        GlobalScope.launch(Dispatchers.IO) {
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
                                    animalInfoLiveData.postValue(response.body()!!.toMutableList())
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
        }
    }


    override fun fetchAnimalDetailedInfo(
        animalInfoLiveData: MutableLiveData<AnimalDetailedInfo>,
        animalId: Int
    ) {
        GlobalScope.launch(Dispatchers.IO) {
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
                                    Log.d(TAG, "onFetchAnimalDetailedInfo " + response.body());
                                    animalInfoLiveData.postValue(response.body())
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
        }
    }

    override fun getFavoritesAnimals(animalInfoLiveData: MutableLiveData<List<ShortAnimalInfo>>,
                                     animalId: String
    ): List<ShortAnimalInfo> {
        GlobalScope.launch(Dispatchers.IO) {
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
                                    Log.d(TAG, "AnimalFavoriteList " + response.body());
                                    animalInfoLiveData.postValue(response.body())
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
        }
        return animalInfoLiveData;
    }

    override fun getAnimalTypes(): List<AnimalType> { //TODO enqueue !
        var list = mutableListOf<AnimalType>()
        GlobalScope.launch(Dispatchers.IO) {
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
        }
        return list
    }

    override fun getAnimalInfoById(): List<Animal> {
        var list = mutableListOf<Animal>()
        return list
    }

}