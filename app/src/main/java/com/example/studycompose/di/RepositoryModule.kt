package com.example.studycompose.di

import com.example.studycompose.network.ApiService
import com.example.studycompose.network.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        apiService: ApiService
    ): MovieRepository {
        return MovieRepository(apiService)
    }
}