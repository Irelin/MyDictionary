package com.example.core.di

import android.app.Application

abstract class BaseApp : Application() {


    companion object {

        lateinit var appComponent: AppComponent
    }
}
