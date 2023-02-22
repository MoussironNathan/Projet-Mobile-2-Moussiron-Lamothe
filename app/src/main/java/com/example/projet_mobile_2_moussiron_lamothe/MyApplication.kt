package com.example.projet_mobile_2_moussiron_lamothe

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    fun writeSharedPref(key: String, value: String) {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("account", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun readSharedPref(key:String):String{
        val sharedPreferences: SharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key,"").toString()
    }
}