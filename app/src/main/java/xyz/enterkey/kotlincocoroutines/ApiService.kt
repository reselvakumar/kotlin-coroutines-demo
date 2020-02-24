package xyz.enterkey.kotlincocoroutines

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import xyz.enterkey.kotlincocoroutines.models.PostResponseModel
import xyz.enterkey.kotlincocoroutines.models.UserResponseModel

interface ApiService {


    @GET("/posts?userId=1")
    fun getPosts(@Query("userId")userId: String): Deferred<Response<List<PostResponseModel>>>

    @GET("/users")
    fun getUser(): Deferred<Response<List<UserResponseModel>>>

}