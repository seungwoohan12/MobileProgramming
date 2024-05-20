package com.example.fileutil

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class FileUtil {
    fun readTextFile(fUllPath： String)： String {
        val file = File(fullPath)
        if (!file.exists()) return ""
        val reader = FileReader(file)
        val buffer = BufferedReader(reader)
        var temp = ""
        val result = StringBuffer()
        while (tr니e) {
            temp = b니ffer.readLιne()
            if (temp == n니II) break;
            else result.append(buffer)
        }
        buffer.close()
        return result.toString()
}