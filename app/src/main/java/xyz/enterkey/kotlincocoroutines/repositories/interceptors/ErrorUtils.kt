package xyz.enterkey.kotlincocoroutines.repositories.interceptors

import android.content.Context
import com.squareup.moshi.JsonAdapter
import retrofit2.HttpException
import retrofit2.Response
import xyz.enterkey.kotlincocoroutines.repositories.RepoConstants
import xyz.enterkey.kotlincocoroutines.repositories.models.ResponseModel
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException


/**
 * ErrorUtils.kt
 * @author Selva
 * created on 22-01-2019
 */

object ErrorUtils {

    fun parseError(response: Response<*>): String? {

        val jsonAdapter: JsonAdapter<ResponseModel> = RepoConstants.moshi.adapter<ResponseModel>(ResponseModel::class.java)

        val errorResponse = jsonAdapter.fromJson(response.errorBody().toString())

        return errorResponse?.message
    }

    fun parseThrowable(e: Exception?):Int{
        return when(e?.cause){
            is HttpException -> {
                parseHttpException(
                    e.cause as HttpException
                )
            }
            is SocketTimeoutException -> {
                RepoConstants.NETWORK_ERROR
            }
            is ConnectException -> {
                RepoConstants.NETWORK_ERROR
            }
            else -> {
                RepoConstants.OTHER_ERROR
            }
        }
    }

    fun parseHttpException(t:HttpException):Int{
        if(t.code() != RepoConstants.RESPONSE_SESSION_ERROR){
            return RepoConstants.OTHER_ERROR
        }
        return  t.code()
    }

}