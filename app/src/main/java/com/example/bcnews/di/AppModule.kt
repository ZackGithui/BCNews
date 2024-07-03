package com.example.bcnews.di


import com.example.bcnews.data.NewsApi
import com.example.bcnews.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import kotlin.jvm.internal.Intrinsics.Kotlin


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton

    fun provideApi():NewsApi{
        val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return (
               Retrofit.Builder()
                   .baseUrl(Constants.BASE_URL)
                   .addConverterFactory(MoshiConverterFactory.create(moshi))
                   .build()
                   .create(NewsApi::class.java)


        )
    }


}