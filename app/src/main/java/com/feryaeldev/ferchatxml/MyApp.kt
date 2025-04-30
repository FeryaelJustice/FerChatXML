package com.feryaeldev.ferchatxml

import android.util.Log
import androidx.multidex.MultiDexApplication
import com.google.android.gms.time.TrustedTime
import com.google.android.gms.time.TrustedTimeClient
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : MultiDexApplication() {
    companion object {
        var trustedTime: TrustedTimeClient? = null
    }

    override fun onCreate() {
        super.onCreate()
        TrustedTime.createClient(this).addOnSuccessListener { client ->
            trustedTime = client
        }.addOnFailureListener {
            trustedTime = null
            Log.e("ErrorOnCreate TrustedTime", it.message.orEmpty())
        }
    }
}