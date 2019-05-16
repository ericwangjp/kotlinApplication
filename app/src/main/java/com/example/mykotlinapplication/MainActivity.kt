package com.example.mykotlinapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mykotlinapplication.model.EnumTest
import com.example.mykotlinapplication.util.CommonUtils
import com.example.mykotlinapplication.model.TestBean

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        printfWord("hahaha")
    }

    fun printfWord(txt: String) {
        Log.e("tag", txt)
        val name = "val value"
        var jack: String? = ""
        var let = name?.let { name.length }
        Log.e("tag", "let 返回值：$let")
        val arr = name?.length
        println("tag woshi kotlin:$name$jack-->$arr")
        val text = """
            |first line
            |second line
            |third line""".trimIndent()
        Log.e("tag", text)
        val x = (Math.random() * 100).toInt()
        val text1 = if (x > 5) "x>5" else "x<=5"
        Log.e("tag", "x=$x | $text1")
        val i = x shl 2
        Log.e("tag-->", "$i")
        if (i is Int) {
            val i1 = i as Int
            Log.e("tag", "i=$i1")
        }
        if (i in 0..50) {
            Log.e("tag scope", "i=$i")
        } else {
            Log.e("tag", "not in scope")
        }

        var scope = (Math.random() * 100).toInt()
        var result = when (scope) {
            0, 5 -> "good"
            in 5..20 -> "ok"
            21, 22 -> "haha 21 22"
            in 22..100 -> "22-100"
            else -> "else any"
        }
        Log.e("tag -->", "scope :$scope result: $result")
        var arrayListOf = arrayListOf<String>()
        for (i in 0..10) {
            arrayListOf.add("current $i")
        }
        for (i in 0 until 10) {
            Log.e("tag", arrayListOf.get(i))
        }
        Log.e("tag", "-----分割线------")
        for (i in 10 downTo 1 step 2) {
            Log.e("tag", arrayListOf.get(i))
        }
        for (item in arrayListOf) {
            Log.e("tag", item)
        }
        arrayListOf.forEach {
            Log.e("tag forEach", "foreach value = $it")
        }
        arrayListOf.filter {
            it.contains("1")
        }.forEach {
            Log.e("tag forEach filter", "foreach filter value = $it")
        }
        val mapOf = mutableMapOf<Int, Int>()
        for (i in 0..20) {
            mapOf.put(i, i + 2)
        }
        for ((key, value) in mapOf) {
            Log.e("tag", "key = $key  value = $value")
        }
        val mapOf1 = mapOf(1 to "one", 2 to "two", 3 to "three")
        for ((key, value) in mapOf1) {
            Log.e("tag", "key = $key  value = $value")
        }
        Log.e("tag", "getNumber = ${getNumber("ok--->")}")
        val name1 = CommonUtils.getName(110)
        Log.e("tag", "CommonUtils.getNumber = $name1")
        val testBean = TestBean("jack", 15)
        Log.e("tag", "testBeanr = ${testBean.age}")
        val(mame,age) = TestBean("nike", 18)
        Log.e("tag", "mame = $mame age = $age")
        val mup = 4.mup()
        Log.e("tag", "mup = $mup")
        val large = EnumTest.LARGE.default
        Log.e("tag", "LARGE = $large")

    }

    fun getNumber(value: String): String = "final value=$value"

    fun Int.mup(): Int {
        return this * 3
    }

}
