package com.example.studycompose.network

import com.example.studycompose.model.Movie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface ApiService {
    @GET("movielist.json")
    suspend fun getMovies(): List<Movie>
}