package com.example.justimagegallery.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://picsum.photos/v2/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface PicsumApiService {
    @GET("list?limit=100")
    suspend fun getPhotos(): String
}

object PicsumApi {
    val retrofitService : PicsumApiService by lazy {
        retrofit.create(PicsumApiService::class.java)
    }
}
