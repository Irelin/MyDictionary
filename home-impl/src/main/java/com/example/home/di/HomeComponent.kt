package com.example.home.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.AppComponent
import com.example.core.di.BaseApp
import com.example.core.di.BaseApp.Companion.appComponent
import com.example.home.presentation.HomeViewModel
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [HomeModule::class],
    dependencies = [
        AppComponent::class,
    ]
)
@HomeScope
interface HomeComponent {

//    @Component.Factory
//    interface Factory {
//
//        fun create(homeComponentDependencies: HomeComponentDependencies): HomeComponent
//    }


//    @Component.Builder
//    interface Builder {
//        fun build(context: Context): HomeComponent
//    }

    fun getViewModel(): HomeViewModel

    fun provideViewModelFactory(): ViewModelProvider.Factory

    companion object {

        fun create(): HomeComponent {
            return DaggerHomeComponent
                .builder()
                .appComponent(BaseApp.appComponent)
                .build()
        }

//        fun create(): HomeComponent {
//            return DaggerHomeComponent.builder()
//                .build()
//        }
    }
}
