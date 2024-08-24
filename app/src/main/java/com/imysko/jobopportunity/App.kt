package com.imysko.jobopportunity

import android.app.Application
import com.imysko.jobopportunity.di.AppComponent
import com.imysko.jobopportunity.di.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .build()
    }
}