package xyz.enterkey.kotlincocoroutines.repositories.models

import androidx.annotation.Keep
import com.squareup.moshi.Json

/**
 * ResponseModel.kt
 * @author Selva
 * created on 17/02/2020.
 */

@Keep
data class ResponseModel(
    @Json(name = "code")
    val code: Int?,
    @Json(name = "message")
    val message: String?
)