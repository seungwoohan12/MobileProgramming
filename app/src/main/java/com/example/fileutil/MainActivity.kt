package com.example.fileutil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val testfile = FileUtil()
        var content = testfile.readTextFile("/data/data/files/xyz.txt")

        var content2 = ""
        context.openFileInput("$fileDir.xyz.txt").bufferedReader().useLines { lines ->
            content2 = lines.joinToString("\n")

        }

        var content3 = "Hello\nWorld"
        context.openFileOutput("$file")
    }
}