package com.example.mykotlinapplication.model

/**
 *[类说明]:
 *@author :wjp
 *@date :2019/5/15
 *@version :Vx.x.x
 */
enum class EnumTest constructor(age:Int){
    LARGE(3),
    MIDDLE(2),
    SMALL(1);
    var default:Int =0
//    private set
    init {
        this.default = age
    }
}