package xyz.enterkey.kotlincocoroutines.repositories

import com.squareup.moshi.Moshi

object RepoConstants{

    const val RESPONSE_SUCCESS = 200
    const val RESPONSE_SESSION_ERROR = 401

    const val  NETWORK_ERROR = 9999
    const val  OTHER_ERROR = 1111

    val moshi: Moshi by lazy { Moshi.Builder().build() }

}