package com.example.httplibrary.config

/**
 *[类说明]:
 *@author :wjp
 *@date :2019/6/14
 *@version :Vx.x.x
 */
class HttpConfig {
    companion object {
        const val HTTP_RESPONSE_SUCCESS: Int = 200
        const val HTTP_RESPONSE_ERROR: Int = 500
        const val HTTP_CONNECT_TIME_OUT: Long = 20
        const val HTTP_READ_TIME_OUT: Long = 10
        const val HTTP_WRITE_TIME_OUT: Long = 10
        const val HTTP_MAX_CACHE_SIZE: Long = 10 * 1024 * 1024
        const val HTTP_MAX_CACHE_TIME_WITH_NET: Long = 60
        const val HTTP_MAX_CACHE_TIME_WITHOUT_NET: Long = 60 * 60 * 24
    }
}