package com.imysko.features.vacancyDetail.di

import com.imysko.features.vacancyDetail.domain.usecase.GetVacancyByIdUseCase
import com.imysko.features.vacancyDetail.domain.usecase.GetVacancyByIdUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
internal abstract class BindVacancyDetailUseCase {

    @Binds
    abstract fun bindGetVacancyByIdUseCase(
        getVacancyByIdUseCaseImpl: GetVacancyByIdUseCaseImpl,
    ): GetVacancyByIdUseCase
}
