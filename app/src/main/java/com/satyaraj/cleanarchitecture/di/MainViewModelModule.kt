package com.satyaraj.cleanarchitecture.di

import android.content.res.Resources
import com.satyaraj.cleanarchitecture.data.MainRepositoryImpl
import com.satyaraj.cleanarchitecture.data.remote.MainApi
import com.satyaraj.cleanarchitecture.domain.FetchMainText
import com.satyaraj.cleanarchitecture.domain.Interactor
import com.satyaraj.cleanarchitecture.domain.abstraction.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module(includes = [MainModule::class])
@InstallIn(ViewModelComponent::class)
object MainViewModelModule {

    @Provides
    @ViewModelScoped
    fun providesInteractor(repository: MainRepository): Interactor {
        return Interactor(FetchMainText(repository))
    }

    @Provides
    @ViewModelScoped
    fun providesMainRepository(api: MainApi, resources: Resources): MainRepository {
        return MainRepositoryImpl(api, resources)
    }
}