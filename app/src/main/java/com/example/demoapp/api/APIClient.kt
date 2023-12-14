package com.example.demoapp.api

import com.example.demoapp.utils.BaseApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient {

    companion object{
        fun getRetrofitClient(): Retrofit? {
            var retrofit: Retrofit? = null
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS).build()
            val gson = GsonBuilder()
                .setLenient()
                .create()
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BaseApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build()
            }
            return retrofit
        }
    }

}