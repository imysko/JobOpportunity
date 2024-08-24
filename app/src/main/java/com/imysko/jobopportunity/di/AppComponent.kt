package com.imysko.jobopportunity.di

import com.imysko.jobopportunity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {

    fun inject(activity: MainActivity)
}