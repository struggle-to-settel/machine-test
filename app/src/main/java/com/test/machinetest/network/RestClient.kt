package com.test.machinetest.network

import com.google.gson.GsonBuilder
import com.test.machinetest.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestClient {

    fun get(): Api {
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
        return retrofit.create(Api::class.java)
    }


    private val okHttpClient: OkHttpClient
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val builder = OkHttpClient.Builder()
            builder.callTimeout(5, TimeUnit.MINUTES)
            builder.connectTimeout(5, TimeUnit.MINUTES)
            builder.readTimeout(5, TimeUnit.MINUTES)
            builder.writeTimeout(5, TimeUnit.MINUTES)
            builder.addNetworkInterceptor(httpLoggingInterceptor)
            builder.addInterceptor(Interceptor { chain ->
                val request = chain.request()
                chain.proceed(request)
            })
            return builder.build()
        }
}