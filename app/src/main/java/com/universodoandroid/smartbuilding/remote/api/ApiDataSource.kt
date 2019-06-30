package com.universodoandroid.smartbuilding.remote.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.universodoandroid.smartbuilding.remote.TokenAuthenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiDataSource(val url: String) {

    private val timeOut: Long = 30

    companion object {
        private var instance: ApiDataSource? = null

        fun getInstance(url: String): ApiDataSource {
            if (instance == null) {
                instance =
                    ApiDataSource(url = url)
            }

            return instance!!
        }
    }

    fun <S> createService(serviceClass: Class<S>): S {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.authenticator(TokenAuthenticator())
        httpClient.connectTimeout(timeOut, TimeUnit.SECONDS)
        httpClient.readTimeout(timeOut, TimeUnit.SECONDS)

        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()

        return retrofit.create(serviceClass)
    }

}