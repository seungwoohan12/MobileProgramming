package com.example.widgetsimagebutton2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import com.example.widgetsimagebutton2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val mybinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val myListener by lazy {
        CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                when(buttonView.id) {
                    R.id.checkApple -> Log.d("CheckBox","사과가 선택되었습니다.")
                    R.id.checkBanana -> Log.d("CheckBox","바나나가 선택되었습니다.")
                    R.id.checkOrange -> Log.d("CheckBox","오렌지가 선택되었습니다.")
                }
            } else {
                when(buttonView.id) {
                    R.id.checkApple -> Log.d("CheckBox", "사과가 선택 해제 되었습니다.")
                    R.id.checkBanana -> Log.d("CheckBox", "바나나가 선택 해제 되었습니다.")
                    R.id.checkOrange -> Log.d("CheckBox", "오렌지가 선택 해제 되었습니다.")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mybinding.root)

        mybinding.checkApple.setOnCheckedChangeListener(myListener)
        mybinding.checkBanana.setOnCheckedChangeListener(myListener)
        mybinding.checkOrange.setOnCheckedChangeListener(myListener)

        //       mybinding.checkApple.setOnCheckedChangeListener { buttonView, isChecked ->
        //          if(isChecked) Log.d("CheckBox","사과가 선택되었습니다.")
        //    else Log.d("CheckBox","사과가 선택 해제되었습니다")
        // }
    }
}