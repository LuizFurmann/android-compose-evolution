package com.example.studycompose.model

import java.io.Serializable

data class Movie (
    val name: String,
    val imageUrl: String,
    val desc: String,
    val category: String

    ): Serializable{
    fun doesMatchSearchQuery(query: String): Boolean{
        val marchingCombinations = listOf(
            "$name"
        )
        return marchingCombinations.any{
            it.contains(query, ignoreCase = true)
        }
    }
    }