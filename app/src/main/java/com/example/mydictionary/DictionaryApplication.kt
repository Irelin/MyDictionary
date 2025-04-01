package com.example.mydictionary

import com.example.core.di.BaseApp
import com.example.mydictionary.di.DaggerAppComponentImpl

//@HiltAndroidApp
class DictionaryApplication: BaseApp() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponentImpl.factory().create(this)
    }
}
