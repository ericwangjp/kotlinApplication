package com.example.httplibrary.util

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

/**
 *[类说明]:
 *@author :wjp
 *@date :2019/6/15
 *@version :Vx.x.x
 */
class LogUtil {
    companion object {
        private var openLog: Boolean = false
        fun initLog(isOpen: Boolean) {
            openLog = isOpen
            Logger.addLogAdapter(AndroidLogAdapter())
        }

        fun logD(msg: String) {
            if (openLog) {
                Logger.d(msg)
            }
        }

        fun logD(list: Any) {
            if (openLog) {
                Logger.d(list)
            }
        }

        fun logE(msg: String) {
            if (openLog) {
                Logger.e(msg)
            }
        }

        fun logW(msg: String) {
            if (openLog) {
                Logger.w(msg)
            }
        }

        fun logV(msg: String) {
            if (openLog) {
                Logger.v(msg)
            }
        }

        fun logI(msg: String) {
            if (openLog) {
                Logger.i(msg)
            }
        }

        fun logWTF(msg: String) {
            if (openLog) {
                Logger.wtf(msg)
            }
        }

        fun logJson(json: String?) {
            if (openLog) {
                Logger.json(json)
            }
        }

        fun logXml(xml: String?) {
            if (openLog) {
                Logger.xml(xml)
            }
        }


    }
}