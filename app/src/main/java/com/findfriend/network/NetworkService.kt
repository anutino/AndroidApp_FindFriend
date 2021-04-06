package com.findfriend.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.findfriend.data.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


open class NetworkService {
    private var baseUrl = "http://10.0.3.2:8080"

    //"http://192.168.1.38:8080/" http://10.0.3.2:8080/findfriend-0.0.1/findAllAnimals http://10.0.3.2:8080/findfriend/

    // http://10.0.3.2:8080/findfriend-0.0.1/animal-short-info-list
    private var mRetrofit: Retrofit

    companion object {
        var instance = NetworkService()
        const val TAG: String = "NetworkService"
    }

    constructor() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
//            .connectTimeout(5000, TimeUnit.SECONDS)
//            .readTimeout(5000, TimeUnit.SECONDS)
//            .writeTimeout(5000, TimeUnit.SECONDS)

        mRetrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
    }

    private fun getJsonApi(): JSONPlaceHolderApi {
        return NetworkService().mRetrofit.create(JSONPlaceHolderApi::class.java)
    }

    //findMainImages {"result":"3","categoryList":[{"id":"0","name":"dog","img_url":"http"},{"id":"1","name":"cat","img_url":"http"},{"id":"2","name":"dog_cat","img_url":"http"}]}
    fun getAnimalTypes(): List<AnimalType> { //TODO enqueue !
        var list = mutableListOf<AnimalType>()
        instance
            .getJsonApi()
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
        return list
    }

    fun onFetchAnimalShortInfoList(animalInfoLiveData: MutableLiveData<List<ShortAnimalInfo>>) { //TODO its coorect postValue call ????
        Log.d(TAG, "onFetchAnimalShortInfoList ");
        instance
            .getJsonApi()
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

    fun fetchAnimalShortInfoListFilteredByType(
        animalInfoLiveData: MutableLiveData<List<ShortAnimalInfo>>, type: Int
    ) {
        instance
            .getJsonApi()
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

    fun onFetchAnimalsShortInfoListFilteredByAgeAndType(
        animalInfoLiveData: MutableLiveData<List<ShortAnimalInfo>>,
        minAge: String,
        maxAge: String,
        type: Int
    ) {
        instance
            .getJsonApi()
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
                        Log.d(Companion.TAG, "not success");
                    }
                }

                override fun onFailure(call: Call<List<ShortAnimalInfo>>, t: Throwable) {
                    Log.d(Companion.TAG, "onFailure");
                }
            })
    }

    fun onFetchAnimalShortListFilteredByAge(
        animalInfoLiveData: MutableLiveData<List<ShortAnimalInfo>>, minAge: String, maxAge: String
    ) {
        instance
            .getJsonApi()
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
                        Log.d(Companion.TAG, "not success");
                    }
                }

                override fun onFailure(call: Call<List<ShortAnimalInfo>>, t: Throwable) {
                    Log.d(Companion.TAG, "onFailure");
                }
            })
    }

    fun onFetchAnimalDetailedInfo(
        animalInfoLiveData: MutableLiveData<AnimalDetailedInfo>,
        animalId: Int
    ) {
        instance
            .getJsonApi()
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
                        Log.d(Companion.TAG, "not success");
                    }
                }

                override fun onFailure(call: Call<AnimalDetailedInfo>, t: Throwable) {
                    Log.d(Companion.TAG, "onFailure");
                }
            })
    }


    fun getFavoritesAnimals(animalInfoLiveData: MutableLiveData<List<ShortAnimalInfo>>,
                            animalId: String
    ) {
            instance
                .getJsonApi()
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
                            Log.d(Companion.TAG, "not success");
                        }
                    }

                    override fun onFailure(call: Call<List<ShortAnimalInfo>>, t: Throwable) {
                        Log.d(Companion.TAG, "onFailure");
                    }
                })
        }

    fun getAnimalInfoById(): List<Animal> {
        var list = mutableListOf<Animal>()
        return list
    }

}