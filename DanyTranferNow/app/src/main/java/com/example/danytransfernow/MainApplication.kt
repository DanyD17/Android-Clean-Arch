package com.example.danytransfernow

import android.app.Application
import android.content.Context
import com.example.danytransfernow.feature.login.domain.model.User
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MainApplication : Application() {
    companion object{
        lateinit var user: User
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

    }

}