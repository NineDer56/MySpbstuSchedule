package com.example.myspbstuschedule.di

import com.example.myspbstuschedule.data.ScheduleRepositoryImpl
import com.example.myspbstuschedule.data.network.api.NetworkApi
import com.example.myspbstuschedule.data.network.mapper.NetworkMapper
import com.example.myspbstuschedule.domain.repository.ScheduleRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    @Singleton
    fun bindScheduleRepositoryImplToInterface(impl : ScheduleRepositoryImpl) :ScheduleRepository

    companion object {
        private const val BASE_URL = "https://ruz.spbstu.ru/api/v1/ruz/"

        @Provides
        @Singleton
        fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        @Provides
        @Singleton
        fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit
        }

        @Provides
        @Singleton
        fun provideNetworkApi(retrofit: Retrofit): NetworkApi {
            return retrofit.create(NetworkApi::class.java)
        }

        @Provides
        @Singleton
        fun provideNetworkMapper() : NetworkMapper{
            return NetworkMapper
        }

    }

}