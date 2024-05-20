package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.diceroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnRoll.setOnClickListener {

            val rollResult:Int = (1..6).random()

            binding.diceResult.setText(Integer.toString(rollResult))

            when(rollResult) {
                1 ->  binding.diceImage.setImageResource(R.drawable.dice1)
                2 ->  binding.diceImage.setImageResource(R.drawable.dice2)
                3 ->  binding.diceImage.setImageResource(R.drawable.dice3)
                4 ->  binding.diceImage.setImageResource(R.drawable.dice4)
                5 ->  binding.diceImage.setImageResource(R.drawable.dice5)
                6 ->  binding.diceImage.setImageResource(R.drawable.dice6)
            }



        }
    }
}