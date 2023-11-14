package com.mglabs.eventme.di

import com.mglabs.eventme.data.source.DefaultLocalDataSource
import com.mglabs.eventme.data.source.DefaultRemoteDataSource
import com.mglabs.eventme.data.source.LocalDataSource
import com.mglabs.eventme.data.source.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindsRemoteDataSource(
        defaultRemoteDataSource: DefaultRemoteDataSource
    ): RemoteDataSource

    @Binds
    abstract fun bindsLocalDataSource(
        defaultLocalDataSource: DefaultLocalDataSource
    ): LocalDataSource
}