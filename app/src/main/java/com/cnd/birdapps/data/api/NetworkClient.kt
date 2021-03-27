package com.cnd.birdapps.data.api

import com.cnd.birdapps.BuildConfig.BASE_URL_HTTP
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 ** Written by CND_Studio 13/03/2021 23.43.
 ** Author @JoeFachrizal
 ** Happy Code...
 **/
class NetworkClient {
    private lateinit var retrofit: Retrofit

    private var okHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    private fun clientHttp(): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_HTTP)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    fun apiHttp(): Routes {
        return clientHttp().create(Routes::class.java)
    }
}