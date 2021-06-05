package com.satyaraj.cleanarchitecture.di

import android.content.Context
import android.content.res.Resources
import com.satyaraj.cleanarchitecture.data.remote.MainApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    fun providesResources(@ApplicationContext context: Context): Resources {
        return context.resources
    }

    @Provides
    fun providesMainApi(retrofit: Retrofit) : MainApi{
        return retrofit.create(MainApi::class.java)
    }
}