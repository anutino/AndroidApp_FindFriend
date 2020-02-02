package com.afokeeva.findfriend.network

import com.afokeeva.findfriend.data.Category
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


open class NetworkService {
    private var BASE_URL = "http://192.168.1.38:8080/" //"http:localhost:8080/"
    private var retrofit: Retrofit

   private constructor() {
       val interceptor = HttpLoggingInterceptor()
       interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

       val client = OkHttpClient.Builder()
           .addInterceptor(interceptor)

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
    }

    companion object{
        var  instance = NetworkService()

    }



    fun getJSONApi(): JSONPlaceHolderApi {
        return NetworkService()
            .retrofit.create(JSONPlaceHolderApi::class.java)
    }

    suspend fun getCategories() : List<Category> {
        var list = mutableListOf<Category>()
         instance
                .getJSONApi()
                .findMainImages()
                .enqueue(object : Callback<List<Category>> {
                    override fun onResponse(
                        call: Call<List<Category>>,
                        response: Response<List<Category>>
                    ) {
                        if (response.isSuccessful) {
                            if (response.body() != null){
                                    try {
                                        //var jsonArray = JsonArray getJSONArray
                                        var json = JSONObject(response.body().toString())
                                        println("Request Successful")
                                        var t = Category(
                                            json.getInt("id"),
                                            json.getString("name"),
                                            json.getString("img_url")
                                        )
                                        list.add(t)
                                    } catch (e: JSONException) {
                                        e.printStackTrace()
                                    }
                            }
                         } else {
                            print("not success")
                        }
                    }

                    override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                        print("onFailure")
                    }
                })
//        var list = mutableListOf<Category>()
//        list.add(Category(1, " ", "http"))
//        list.add(Category(1, " ", "http"))
//        list.add(Category(1, " ", "http"))
//        list.add(Category(1, " ", "http"))
        return list
    }
}