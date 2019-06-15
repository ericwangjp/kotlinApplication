package com.example.httplibrary.service

import com.example.httplibrary.model.SendMsgResp
import com.example.httplibrary.model.CommonResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

/**
 *[类说明]:
 *@author :wjp
 *@date :2019/6/14
 *@version :Vx.x.x
 */
interface NetService {
    @POST
    fun sendSmsCode(@Url url: String, @Body requestBody: RequestBody): Observable<CommonResponse<SendMsgResp>>
}