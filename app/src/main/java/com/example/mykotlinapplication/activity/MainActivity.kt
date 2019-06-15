package com.example.mykotlinapplication.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.mykotlinapplication.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_text.setText("kotlin text")
        tv_text.setOnClickListener { Toast.makeText(this,"点击了我",Toast.LENGTH_SHORT).show() }
    }


}
