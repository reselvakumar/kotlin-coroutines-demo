package xyz.enterkey.kotlincocoroutines.repositories

import xyz.enterkey.kotlincocoroutines.repositories.models.CallResponse
import retrofit2.await
import xyz.enterkey.kotlincocoroutines.ApiService
import xyz.enterkey.kotlincocoroutines.models.PostResponseModel
import xyz.enterkey.kotlincocoroutines.models.UserResponseModel

class DemoRepository(private val api: ApiService) : BaseRepository() {

    suspend fun getUser(): CallResponse<List<UserResponseModel>> {
        return safeApiCall(
            call = { api.getUser().await() }
        )
    }

    suspend fun getPost(userId: String): CallResponse<List<PostResponseModel>> {
        return safeApiCall(
            call = { api.getPosts(userId).await() }
        )
    }

}