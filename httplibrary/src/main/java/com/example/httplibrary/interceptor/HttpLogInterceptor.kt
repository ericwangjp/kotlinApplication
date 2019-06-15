package com.example.httplibrary.interceptor

import com.example.httplibrary.util.LogUtil
import okhttp3.logging.HttpLoggingInterceptor

/**
 *[类说明]:
 *@author :wjp
 *@date :2019/6/15
 *@version :Vx.x.x
 */
class HttpLogInterceptor private constructor() {
    companion object {
        const val TAG: String = "『==>HttpNetworkLog<==』"
        private var instance: HttpLogInterceptor? = null
            get() {
                if (field == null) {
                    field = HttpLogInterceptor()
                }
                return field
            }

        fun getLogInstance() = instance!!

    }

    fun getHttpLogInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            LogUtil.logD(it)
        })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
}