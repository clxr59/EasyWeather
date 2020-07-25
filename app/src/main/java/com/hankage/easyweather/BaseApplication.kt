package com.hankage.easyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * Author: cheers
 * Time ： 2020-07-25
 * Description ：
 */
class BaseApplication : Application() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var sContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        sContext = this
    }
}