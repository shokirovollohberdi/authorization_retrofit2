package uz.shokirov.authorizationretrofit2.service

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import uz.shokirov.authorizationretrofit2.model.LoginModel

interface ApiService {

    @FormUrlEncoded
    @POST("token")
    fun checkUser(@Field("username") username: String, @Field("password") password: String, ): Call<LoginModel>
}