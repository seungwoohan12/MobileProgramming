package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text1: TextView = findViewById(R.id.textSay)
        val btn1: Button = findViewById(R.id.btnSay)

        btn1.setOnClickListener {
            text1.setText("Hello Kotlin!!!!")
        }

        // val binding1 = ActivityMainBinding.inflate(layoutInflater)
        // setContentView(binding1.root)

        // binding1.btnSay.setOnClickListener{
        //    binding1.textSay.text = "Hello Kotlin"
        // }
   // }
      }