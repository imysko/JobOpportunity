package com.imysko.data.offers.di

import android.content.Context
import com.imysko.data.offers.domain.repository.OffersRepository
import com.imysko.data.offers.mock.MockOffersRepository
import dagger.Module
import dagger.Provides

@Module
object OffersRepositoryProvider {

    @Provides
    fun providerMockOffersRepository(
        context: Context
    ): OffersRepository = MockOffersRepository(context)
}
