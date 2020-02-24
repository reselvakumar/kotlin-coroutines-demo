package xyz.enterkey.kotlincocoroutines.livedata

/**
 * UILoader.kt
 * @author Selva
 * created on 17/02/2020.
 */

data class UILoader<out T>(val status: UILoaderStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): UILoader<T> {
            return UILoader(
                UILoaderStatus.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(msg: String?): UILoader<T> {
            return UILoader(
                UILoaderStatus.ERROR,
                null,
                msg
            )
        }

        fun <T> loading(): UILoader<T> {
            return UILoader(
                UILoaderStatus.LOADING,
                null,
                null
            )
        }
    }
}