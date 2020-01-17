package com.afokeeva.findfriend.infoFromServer

import com.afokeeva.findfriend.infoFromServer.Tables.Animal
import com.afokeeva.findfriend.infoFromServer.Tables.Image
import com.afokeeva.findfriend.infoFromServer.Tables.Test
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.JsonObject



 class ServerRequest {
     companion object Requests {

         fun test() : MutableList<Test>  { //https://medium.com/@antobeslie25/android-recyclerview-pagination-with-paging-library-positionaldatasource-using-retrofit-mvvm-de811ef56b07
             var tList = mutableListOf<Test>()
             NetworkService.companionFun()
                 .instance
                 .getJSONApi()
                 .test("Beyonce")
                 .enqueue(object : Callback<ResponseBody>{
                     override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>
                     ) {
                         if (response.isSuccessful) {
                             print("success")
                             val post = JsonObject().get(response.body().toString()).asJsonObject
                             var t = Test(post.get("trackId").asInt, post.get("artistName").asString, post.get("artworkUrl30").asString)
                             tList.add(t)
                          } else {
                             print("not success")
                         }
                     }

                     override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                         print("onFailure")
                     }
                 })

             print("_____________ " + tList.size)
             return tList
         }

         fun findAllAnimals() {
             NetworkService.companionFun()
                 .instance
                 .getJSONApi()
                 .findAllAnimals()
                 .enqueue(object : Callback<List<Animal>> {
                     override fun onResponse(
                         call: Call<List<Animal>>,
                         response: Response<List<Animal>>
                     ) {
                         if (response.isSuccessful) {
                             print("success")
                         } else {
                             print("not success")
                         }
                     }

                     override fun onFailure(call: Call<List<Animal>>, t: Throwable) {
                         print("onFailure")
                     }
                 })
         }

         fun findAllAnimalsByType(id_type : Int) {
             NetworkService.companionFun()
                 .instance
                 .getJSONApi()
                 .findAllAnimalsByType(id_type)
                 .enqueue(object : Callback<List<Animal>> {
                     override fun onResponse(
                         call: Call<List<Animal>>,
                         response: Response<List<Animal>>
                     ) {
                         if (response.isSuccessful) {
                             print("success")
                         } else {
                             print("not success")
                         }
                     }

                     override fun onFailure(call: Call<List<Animal>>, t: Throwable) {
                         print("onFailure")
                     }
                 })
         }

         fun getImagesByIdAnimal(id_animal : Int) {
             NetworkService.companionFun()
                 .instance
                 .getJSONApi()
                 .getImagesByIdAnimal(id_animal)
                 .enqueue(object : Callback<List<Image>> {
                     override fun onResponse(
                         call: Call<List<Image>>,
                         response: Response<List<Image>>
                     ) {
                         if (response.isSuccessful) {
                             print("success")
                         } else {
                             print("not success")
                         }
                     }

                     override fun onFailure(call: Call<List<Image>>, t: Throwable) {
                         print("onFailure")
                     }
                 })
         }
     }
}