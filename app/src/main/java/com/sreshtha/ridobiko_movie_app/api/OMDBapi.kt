package com.sreshtha.ridobiko_movie_app.api

import com.sreshtha.ridobiko_movie_app.model.Show
import com.sreshtha.ridobiko_movie_app.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDBApi {

    @GET("/")
    suspend fun getMovie(
        @Query("t") title:String,
        @Query("apikey") apiKey: String = Constants.API_KEY,
        @Query("type") type:String = "movie"
    ):Response<Show>


    @GET("/")
    suspend fun getSeries(
        @Query("t") title: String,
        @Query("apikey") apiKey: String=Constants.API_KEY,
        @Query("type") type: String = "series"
    ):Response<Show>


    @GET("/")
    suspend fun getShow(
        @Query("t") title: String,
        @Query("apikey") apiKey: String= Constants.API_KEY
    ):Response<Show>

}