package uz.shokirov.authorizationretrofit2.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class DataSources {
    private lateinit var sharedPreferences: SharedPreferences

    fun setLogin(context: Context, isRegister: Boolean) {
        sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor?.putBoolean("registor", isRegister)?.apply()

    }

    fun isLogin(context: Context): Boolean {
        sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("registor", false)
    }

    fun saveToken(context: Context, token: String) {
        Log.e("TAG", "saveToken: $token")
        sharedPreferences = context.getSharedPreferences("key_token", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor?.putString("forToken", token)?.apply()

    }

    fun saveRole(context: Context, token: String) {
        Log.e("TAG", "saveToken: $token")
        sharedPreferences = context.getSharedPreferences("key_role", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor?.putString("forToken", token)?.apply()

    }

    fun getToken(context: Context): String {
        sharedPreferences = context.getSharedPreferences("key_token", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("forToken", "")
        return token!!
    }
}