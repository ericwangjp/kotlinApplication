package com.example.httplibrary.interceptor

import android.content.Context
import com.example.httplibrary.config.HttpConfig
import com.example.httplibrary.util.NetworkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor


/**
 *[类说明]:
 *@author :wjp
 *@date :2019/6/15
 *@version :Vx.x.x
 */
class HttpCacheInterceptor {
    companion object {
        fun getCacheInterceptor(context: Context): Interceptor {
            return Interceptor { chain ->
                var request = chain.request()
                if (!NetworkUtils.checkNet(context)) {
                    //无网络下强制使用缓存，无论缓存是否过期,此时该请求实际上不会被发送出去。
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build()
                }

                val response = chain.proceed(request)
                if (NetworkUtils.checkNet(context)) {//有网络情况下，根据请求接口的设置，配置缓存。
                    //这样在下次请求时，根据缓存决定是否真正发出请求。
                    val cacheControl = request.cacheControl().toString()
                    //当然如果你想在有网络的情况下都直接走网络，那么只需要
                    //将其超时时间这是为0即可:String cacheControl="Cache-Control:public,max-age=0"
                    response.newBuilder()
                        .header("Cache-Control", cacheControl)
//                            自定义缓存策略
//                            .header("Cache-Control", "public, max-age=" + HttpConfig.MAX_CACHE_TIME_WITH_NET)// read from cache for 1 minute
                        .removeHeader("Pragma")
                        .build()
                } else {
                    //无网络
                    response.newBuilder()
//                            .header("Cache-Control", "public,only-if-cached,max-stale=360000")
                        .header(
                            "Cache-Control",
                            "public,only-if-cached,max-stale=" + HttpConfig.HTTP_MAX_CACHE_TIME_WITHOUT_NET
                        )// tolerate 24-hours stale
                        .removeHeader("Pragma")
                        .build()
                }
            }
        }
    }
}