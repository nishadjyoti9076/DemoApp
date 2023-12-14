package com.example.demoapp.utils

import android.content.Context
import android.net.ConnectivityManager

class CheckNetwork {

    companion object{
        fun isNetworkAvailable(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = cm.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }



}
