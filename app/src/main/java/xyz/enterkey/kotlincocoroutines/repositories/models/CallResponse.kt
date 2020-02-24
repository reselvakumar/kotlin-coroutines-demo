package xyz.enterkey.kotlincocoroutines.repositories.models

/**
 * CallResponse.kt
 * @author Selva
 * created on 17/02/2020.
 */


data class CallResponse<out T>(val status: CallResponseStatus, val data: T?,val code: Int, val message: String?) {
    companion object {
        fun <T> success(data: T?): CallResponse<T> {
            return CallResponse(
                CallResponseStatus.SUCCESS,
                data,
                0,
                null
            )
        }

        fun <T> error(msg: String?, code: Int): CallResponse<T> {
            return CallResponse(
                CallResponseStatus.ERROR,
                null,
                code,
                msg
            )
        }

    }
}