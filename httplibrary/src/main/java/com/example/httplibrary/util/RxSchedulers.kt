package com.example.httplibrary.util

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *[类说明]:
 *@author :wjp
 *@date :2019/6/15
 *@version :Vx.x.x
 */
class RxSchedulers {
    companion object {
        fun <T> applySchedulers(): ObservableTransformer<T, T> {
            return ObservableTransformer {
                it.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}