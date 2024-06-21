package com.example.bcnews.di

import com.example.bcnews.data.NewsApi
import com.example.bcnews.data.RepositoryImp
import com.example.bcnews.domain.repository.NewsRepository
import com.example.bcnews.util.Constants
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton

    fun provideApi():NewsApi{

        return (
               Retrofit.Builder()
                   .baseUrl(Constants.BASE_URL)
                   .addConverterFactory(MoshiConverterFactory.create())
                   .build()
                   .create(NewsApi::class.java)


        )
    }


}