package xyz.enterkey.kotlincocoroutines.repositories

import xyz.enterkey.kotlincocoroutines.repositories.models.CallResponse
import xyz.enterkey.kotlincocoroutines.repositories.models.Result
import retrofit2.Response
import xyz.enterkey.kotlincocoroutines.repositories.interceptors.ErrorUtils
import java.lang.Exception

/**
 * BaseRepository.kt
 * @author Selva
 * created on 17/02/2020.
 */

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): CallResponse<T> {

        val result: Result<T> = safeApiResult(call)

        return when (result) {
            is Result.Success ->
                CallResponse.success(result.data)
            is Result.Error -> {
                CallResponse.error(result.message,result.code)
            }
        }

    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>): Result<T> {
        try{
            val response = call.invoke()

            return if (response.isSuccessful){
                if(response.body() != null){
                    Result.Success(response.body()!!)
                }else{
                    Result.Error(RepoConstants.OTHER_ERROR,null)
                }
            }else{
                Result.Error(response.code() ,
                    ErrorUtils.parseError(response) ?: response.message())
            }

        }catch (e:Exception){
            return Result.Error(ErrorUtils.parseThrowable(e),e.localizedMessage)
        }
    }

}