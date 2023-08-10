package com.test.machinetest.utils

import java.text.SimpleDateFormat
import java.util.Locale

object Functions {

    fun String.getDateWithTime(): String {
        var date = ""
        SimpleDateFormat("yyyy-mm-dd hh:mm:ss", Locale.getDefault()).let { input ->
            SimpleDateFormat("dd MMM yyyy hh:mm:aa", Locale.getDefault()).let { outputOne ->
                date = input.parse(this)?.let { outputOne.format(it) }.toString()
            }
        }
        return date
    }
}