package com.sreshtha.ridobiko_movie_app.di

import com.sreshtha.ridobiko_movie_app.api.OMDBapi
import com.sreshtha.ridobiko_movie_app.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun providesOMDBapi():OMDBapi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(OMDBapi::class.java)
    }

}