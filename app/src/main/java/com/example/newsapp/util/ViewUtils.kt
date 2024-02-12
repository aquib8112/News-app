package com.example.newsapp.util

import android.content.Context
import android.widget.Toast
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo ?: return false
    return networkInfo.isConnected
}