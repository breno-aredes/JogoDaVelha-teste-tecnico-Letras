package com.example.jogodavelha_letras

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SecurityPreferences(context: Context) {
    private val security: SharedPreferences =
        context.getSharedPreferences("a", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun storeString(key:String,string: String){
        security.edit().putString(key,string).apply()
    }

    fun getString(key: String): String{
       return security.getString(key,"") ?: ""
    }

}