package com.example.httplibrary.callback

/**
 *[类说明]:
 *@author :wjp
 *@date :2019/6/15
 *@version :Vx.x.x
 */
interface NetworkCallBack<T> {
    fun onStart()
    fun onSuccess(data: T)
    fun onFailure(msg: String, returnCode: Int)
    fun onError(msg: String)
    fun onFinish()
    fun onProgressUpdate(progress: Float)
}