package xyz.enterkey.kotlincocoroutines.repositories

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import xyz.enterkey.kotlincocoroutines.BuildConfig
import java.util.concurrent.TimeUnit

/**
 * RetrofitFactory.kt
 * @author Selva
 * created on 17/02/2020.
 */

object RetrofitFactory {

    private val client =
        if (BuildConfig.DEBUG) {
            OkHttpClient().newBuilder()
                .readTimeout(150L, TimeUnit.SECONDS)
                .connectTimeout(40L, TimeUnit.SECONDS)
                .build()
        } else {
            OkHttpClient().newBuilder()
                .readTimeout(150L, TimeUnit.SECONDS)
                .connectTimeout(40L, TimeUnit.SECONDS)
                .build()
        }

    fun retrofit(): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.SERVER_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    fun retrofit(baseURl:String): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(baseURl)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

}


