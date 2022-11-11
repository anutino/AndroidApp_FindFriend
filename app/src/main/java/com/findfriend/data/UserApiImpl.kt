package com.findfriend.data

import com.findfriend.domain.UserApi
import com.findfriend.domain.model.DescriptionMediaAnimalInfo
import retrofit2.Call

class UserApiImpl : UserApi{
    override fun createUser(name: String,
                            login: String,
                            password: String
    ): Call<DescriptionMediaAnimalInfo> {
        TODO("Not yet implemented")
    }

    override fun deleteUserById(userId: Int): Call<DescriptionMediaAnimalInfo> {
        TODO("Not yet implemented")
    }

    override fun signIn(login: String, password: String): Call<DescriptionMediaAnimalInfo> {
        TODO("Not yet implemented")
    }
}