package com.example.myspbstuschedule.di

import android.content.Context
import com.example.myspbstuschedule.data.datastore.SettingsDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataStoreModule {

    companion object {

        @Singleton
        @Provides
        fun provideSettingsDataStore(
            @ApplicationContext context : Context
        ) : SettingsDataStore {
            return SettingsDataStore(context)
        }

    }
}