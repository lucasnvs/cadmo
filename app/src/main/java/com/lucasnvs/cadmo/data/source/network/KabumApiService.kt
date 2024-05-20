package com.lucasnvs.cadmo.data.source.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL =
    "https://servicespub.prod.api.aws.grupokabum.com.br/home/v1/home/"

private val json = Json { ignoreUnknownKeys = true }

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

@Serializable
data class ProductResponse(
    @SerialName("produtos")
    val produtos: List<NetworkProduct>
)

interface KabumApiService {
    @GET("produto")
    suspend fun getProducts(): ProductResponse
}

object KabumApi {
    val retrofitService: KabumApiService by lazy {
        retrofit.create(KabumApiService::class.java)
    }
}