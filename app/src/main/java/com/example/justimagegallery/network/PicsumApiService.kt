package com.example.justimagegallery.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://picsum.photos/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PicsumApiService {
    @GET("list?limit=100")
    suspend fun getPhotos(): List<PicsumPhoto>
}

object PicsumApi {
    val retrofitService : PicsumApiService by lazy {
        retrofit.create(PicsumApiService::class.java)
    }
}
