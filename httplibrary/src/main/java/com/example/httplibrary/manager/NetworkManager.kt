package com.example.httplibrary.manager

import android.content.Context
import com.example.httplibrary.config.HttpConfig
import com.example.httplibrary.interceptor.HttpCacheInterceptor
import com.example.httplibrary.interceptor.HttpLogInterceptor
import com.example.httplibrary.util.LogUtil
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


/**
 *[类说明]:
 *@author :wjp
 *@date :2019/6/15
 *@version :Vx.x.x
 */
class NetworkManager private constructor() {
    private var retrofit: Retrofit? = null

    companion object {
        val instance: NetworkManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkManager()
        }

    }

    fun init(context: Context, baseUrl: String) {
//        初始化log打印
        LogUtil.initLog(true)
        val okHttpClient = OkHttpClient.Builder().connectTimeout(HttpConfig.HTTP_CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(HttpConfig.HTTP_READ_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(HttpConfig.HTTP_WRITE_TIME_OUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addNetworkInterceptor(HttpLogInterceptor.getLogInstance().getHttpLogInterceptor())
            .cache(Cache(File(context.cacheDir, "HttpCache"), HttpConfig.HTTP_MAX_CACHE_SIZE))
            .addNetworkInterceptor(HttpCacheInterceptor.getCacheInterceptor(context))
            .cookieJar(object : CookieJar {
                val cookieStore: HashMap<HttpUrl, List<Cookie>> = HashMap()

                override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                    cookieStore[url] = cookies//保存cookie
                    //也可以使用SP保存
                }

                override fun loadForRequest(url: HttpUrl): List<Cookie> {
                    val cookies = cookieStore[url]//取出cookie
                    return cookies ?: ArrayList()
                }
            })
            .build()

        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun <T> getRequestService(service: Class<T>): T? {
        return retrofit?.create(service)
    }
}