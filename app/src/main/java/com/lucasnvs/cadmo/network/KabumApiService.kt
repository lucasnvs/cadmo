package com.lucasnvs.cadmo.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.lucasnvs.cadmo.model.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL =
    "https://servicespub.prod.api.aws.grupokabum.com.br/home/v1/home/"

private val json = Json { ignoreUnknownKeys = true }

/**
 * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

/**
 * Retrofit service object for creating api calls
 */
@Serializable
data class ProductResponse(
    @SerialName("produtos")
    val produtos: List<Product>
)

interface KabumApiService {
    @GET("produto")
    suspend fun getProducts(): ProductResponse
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object KabumApi {
    val retrofitService: KabumApiService by lazy {
        retrofit.create(KabumApiService::class.java)
    }
}