package com.example.mydictionary

import android.app.Application
import com.example.mydictionary.di.AppComponent
import com.example.mydictionary.di.DaggerAppComponent

//@HiltAndroidApp
class DictionaryApplication: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}