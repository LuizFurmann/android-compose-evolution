package com.example.studycompose.network

import androidx.annotation.WorkerThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.studycompose.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: ApiService){

    var movieListResponse : List<Movie> by mutableStateOf(listOf())
    suspend fun getMovieList():  List<Movie>{
        val movieList = apiService.getMovies()
            movieListResponse = movieList
        return movieListResponse
    }
}
