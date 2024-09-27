package com.imysko.jobopportunity.di

import android.content.Context
import com.imysko.features.searchVacancy.di.SearchVacancyDeps
import com.imysko.jobopportunity.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [],
)
interface AppComponent : SearchVacancyDeps {

    override val appContext: Context

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun appContext(appContext: Context): Builder

        fun build(): AppComponent
    }

    fun inject(activity: MainActivity)
}
