package com.stellar1198.a6thseminar

import android.content.Context

// 별도의 객체화 과정 없이 다른 클래스에서도 접근 가능

object SharedPreferenceController {
    private val USER = "user"
    private val ID = "id"
    private val PWD = "pwd"

    fun setID(context: Context, id : String) {
        var pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        var editor = pref.edit()
        editor.putString(ID, id)
        editor.commit()
    }

    fun getID(context: Context) : String {
        var pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        return pref.getString(ID, "")
    }

    fun setPWD(context: Context, id : String) {
        var pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        var editor = pref.edit()
        editor.putString(PWD, id)
        editor.commit()
    }

    fun getPWD(context: Context) : String {
        var pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        return pref.getString(PWD, "")
    }

    fun clearSPC(context: Context){
        var pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.clear()
        editor.commit()
    }

}