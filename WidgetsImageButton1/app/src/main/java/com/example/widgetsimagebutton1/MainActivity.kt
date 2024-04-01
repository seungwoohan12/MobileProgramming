package com.example.widgetsimagebutton1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.widgetsimagebutton1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val mybinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mybinding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.radioApple -> Log.d("RadioButton", "사과가 선택되었습니다")
                R.id.radioBanana -> Log.d("RadioButton", "바나나가 선택되었습니다")
                R.id.radioOrange -> Log.d("RadioButton", "오렌지가 선택되었습니다")
            }
        }
    }
}