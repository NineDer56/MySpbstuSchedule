package com.example.myspbstuschedule.di

import android.content.Context
import androidx.room.Room
import com.example.myspbstuschedule.data.local.DatabaseMapper
import com.example.myspbstuschedule.data.local.ScheduleDao
import com.example.myspbstuschedule.data.local.ScheduleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseModule {

    companion object {

        private const val DB_NAME = "myspbstuschedule.db"

        @Singleton
        @Provides
        fun provideDatabase(
            @ApplicationContext context: Context
        ): ScheduleDatabase {
            return Room.databaseBuilder(
                context = context,
                klass = ScheduleDatabase::class.java,
                name = DB_NAME
            ).build()
        }

        @Singleton
        @Provides
        fun provideDao(db : ScheduleDatabase) : ScheduleDao{
            return db.dao()
        }

        @Singleton
        @Provides
        fun provideDatabaseMapper() : DatabaseMapper {
            return DatabaseMapper
        }
    }
}