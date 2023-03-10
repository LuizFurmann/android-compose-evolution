package com.example.studycompose.viewModel

import android.content.Context
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studycompose.model.Movie
import com.example.studycompose.network.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    var movieListResponse : List<Movie> by mutableStateOf(listOf())
    var errorMessage : String by mutableStateOf("")

    fun movieFilter(movieList: List<Movie>, context: Context, state: MutableState<TextFieldValue>): List<Movie>{

        val searchedText = state.value.text

        if (searchedText.isEmpty()) {
            return movieList
        } else {
            val resultList = ArrayList<Movie>()
            for (item in movieList) {
                if (item.name.lowercase(Locale.getDefault())
                        .contains(searchedText.lowercase(Locale.getDefault()))
                ) {
                    resultList.add(item)
                }
            }
            return resultList
        }
    }

    fun getMovieList(): List<Movie>{
        viewModelScope.launch {
            try{
                val movieList = movieRepository.getMovieList()
                movieListResponse = movieList

            }
            catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
        return movieListResponse
    }
}