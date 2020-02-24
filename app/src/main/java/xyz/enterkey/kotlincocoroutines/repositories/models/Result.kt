package xyz.enterkey.kotlincocoroutines.repositories.models

/**
 * Result.kt
 * @author Selva
 * created on 17/02/2020.
 */

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val code:Int, val message: String?) : Result<Nothing>()
}