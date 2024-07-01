package com.example.bcnews.di


import com.example.bcnews.data.NewsApi
import com.example.bcnews.util.Constants
import com.squareup.moshi.Moshi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton

    fun provideApi():NewsApi{
        val moshi: Moshi = Moshi.Builder().build()

        return (
               Retrofit.Builder()
                   .baseUrl(Constants.BASE_URL)
                   .addConverterFactory(MoshiConverterFactory.create(moshi))
                   .build()
                   .create(NewsApi::class.java)


        )
    }


}