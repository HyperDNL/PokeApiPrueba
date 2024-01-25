package com.example.pokeprueba.utils

import android.app.Activity
import android.content.Intent

object NavigationUtils {
    fun redirectToDestination(activity: Activity, destination: Class<*>) {
        val intent = Intent(activity, destination)
        activity.startActivity(intent)
        activity.finishAffinity()
    }
}