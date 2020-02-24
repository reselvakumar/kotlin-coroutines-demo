package  xyz.enterkey.kotlincocoroutines.repositories

import xyz.enterkey.kotlincocoroutines.ApiService
import xyz.enterkey.kotlincocoroutines.BuildConfig


/**
 * APIError.kt
 * @author Selva
 * created on 17/02/2020.
 */

object ApiFactory {

    val apiService = RetrofitFactory.retrofit(BuildConfig.SERVER_URL)
        .create(ApiService::class.java)

}