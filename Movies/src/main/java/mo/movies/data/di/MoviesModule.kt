package mo.movies.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mo.common.RemoteConstants.BASE_URL
import mo.movies.data.db.room.MovieDatabase
import mo.movies.data.db.room.dao.MovieDao
import mo.movies.data.remote.api.MoviesApi
import mo.movies.data.repository.MoviesRepositoryImpl
import mo.movies.domain.repository.MoviesRepository
import mo.movies.domain.useCases.local.AddMovieToFavouriteUseCase
import mo.movies.domain.useCases.local.DropFavouriteMovieTableUseCase
import mo.movies.domain.useCases.local.GetFavouriteMoviesListUseCase
import mo.movies.domain.useCases.local.LocalUseCases
import mo.movies.domain.useCases.local.RemoveMovieFromFavouriteUseCase
import mo.movies.domain.useCases.local.UndoDroppingFavouriteMovieTableUseCase
import mo.movies.domain.useCases.remote.GetMovieDetailsUseCase
import mo.movies.domain.useCases.remote.GetMoviesByCategoryUseCase
import mo.movies.domain.useCases.remote.GetMoviesByGenreUseCase
import mo.movies.domain.useCases.remote.GetMoviesGenreListUseCase
import mo.movies.domain.useCases.remote.RemoteUseCases
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMoviesRepository(api: MoviesApi, movieDao: MovieDao): MoviesRepository {
        return MoviesRepositoryImpl(api, movieDao)
    }

    @Provides
    @Singleton
    fun provideLocalUseCases(repository: MoviesRepository): LocalUseCases {
        return LocalUseCases(
            AddMovieToFavouriteUseCase(repository),
            DropFavouriteMovieTableUseCase(repository),
            RemoveMovieFromFavouriteUseCase(repository),
            UndoDroppingFavouriteMovieTableUseCase(repository),
            GetFavouriteMoviesListUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideRemoteUseCases(repository: MoviesRepository): RemoteUseCases {
        return RemoteUseCases(
            GetMovieDetailsUseCase(repository),
            GetMoviesByCategoryUseCase(repository),
            GetMoviesByGenreUseCase(repository),
            GetMoviesGenreListUseCase(repository)
        )
    }


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        return MovieDatabase.getDatabaseInstance(context)
    }

    @Singleton
    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao {
        return database.getMovieDao()
    }


}