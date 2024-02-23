package com.example.secretdrivers.di

import com.example.secretdrivers.data.db.sddriver.DriverDao
import com.example.secretdrivers.data.remote.ApiDetails
import com.example.secretdrivers.data.remote.ApiService
import com.example.secretdrivers.data.repository.SdRepository
import com.example.secretdrivers.data.repository.SdRepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideSdRepository(driverDao: DriverDao): SdRepository {
        return SdRepositoryImpl(driverDao)
    }

    @Provides
    fun providesGson(

    ): Gson {
        return Gson()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
      return OkHttpClient.Builder().apply {
            this.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()

    }

    @Provides
    fun provideRetrofit(
        okgHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiDetails.BASE_URL)
            .client(okgHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ) : ApiService {
        return retrofit.create(ApiService::class.java)
    }
}