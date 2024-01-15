package mo.movies.domain.repository

import mo.common.Result
import mo.movies.domain.models.MovieGenre
import mo.movies.domain.models.MovieGlobal
import mo.movies.domain.models.MoviesList

interface MoviesRepository {
    /**
     *  Remote functions
     */
    suspend fun getMoviesByCategory(category: String, pageNumber: Int = 1): Result<MoviesList>

    suspend fun getMovieDetails(id: Int): Result<MovieGlobal>

    suspend fun getMoviesByGenre(genreId: Int, pageNumber: Int = 1): Result<MoviesList>


    suspend fun getMoviesGenreList(): Result<List<MovieGenre>>


    /**
     * Local functions
     */

    suspend fun removeMovieFromFavourite(movie: MovieGlobal): Result<Boolean>

    suspend fun addMovieToFavourite(movie: MovieGlobal): Result<Boolean>

    suspend fun dropFavouriteMoviesTable(): Result<Boolean>

    suspend fun undoDroppingFavouriteMovieTable(movieList: List<MovieGlobal>): Result<Boolean>

    suspend fun getFavouriteMoviesList(pageNumber: Int, offset: Int): Result<List<MovieGlobal>>

    suspend fun getAllFavouriteMovies(): Result<List<MovieGlobal>>


}