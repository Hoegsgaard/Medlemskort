package com.example.medlemskort

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginButton.setOnClickListener() {
            val intent = Intent(this, navigation::class.java)
            startActivity(intent);
        }
        val bum = arrayListOf<Int>(2,4,6,4,34,45,45)
        val dum = bum[20]

    }
}
