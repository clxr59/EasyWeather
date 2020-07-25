package com.hankage.easyweather.logic.network

import com.hankage.easyweather.BaseApplication
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Author: cheers
 * Time ： 2020-07-25
 * Description ： 单例对象
 */
object RetrofitCreator {

    private const val BASE_URL = "https://api.caiyunapp.com/"
    private const val TIME_OUT = 15L
    private const val CACHE_FILE_NAME = "responses"
    private const val MAX_CACHE_SIZE = 1024 * 1024 * 10L

    private val mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    private fun getOkHttpClient() : OkHttpClient{
        val builder = OkHttpClient.Builder()
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        val cacheFile = File(BaseApplication.sContext.cacheDir.path, CACHE_FILE_NAME)
        val cache = Cache(cacheFile, MAX_CACHE_SIZE)

        builder.cache(cache)
        return builder.build()
    }


    fun <T> create(clazz : Class<T>) : T = mRetrofit.create(clazz)

    /**
     * 泛型实化
     */
    inline fun <reified T> create() : T = create(T::class.java)
}