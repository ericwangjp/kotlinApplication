package com.example.httplibrary.model

import com.example.httplibrary.config.HttpConfig
import java.io.Serializable

/**
 *[类说明]:
 *@author :wjp
 *@date :2019/6/14
 *@version :Vx.x.x
 */
class CommonResponse<T> : Serializable {
    val responseCode: Int? = -1
    val responseMessage: String? = null
    val result: T? = null
    fun isSuccess(): Boolean = responseCode == HttpConfig.HTTP_RESPONSE_SUCCESS
}