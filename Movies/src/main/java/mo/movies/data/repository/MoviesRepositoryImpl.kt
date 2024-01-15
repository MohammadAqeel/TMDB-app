package mo.movies.data.repository

import mo.common.Result
import mo.common.returnResult
import mo.movies.data.db.models.toDb
import mo.movies.data.db.room.dao.MovieDao
import mo.movies.data.remote.api.MoviesApi
import mo.movies.domain.models.MovieGenre
import mo.movies.domain.models.MovieGlobal
import mo.movies.domain.models.MoviesList
import mo.movies.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val movieApi: MoviesApi,
    private val movieDao: MovieDao
) : MoviesRepository {

    /**
     * Remote Functions
     */

    override suspend fun getMoviesByCategory(
        category: String,
        pageNumber: Int
    ): Result<MoviesList> {
        return returnResult {
            val response = movieApi.getMoviesListByCategory(category, pageNumber = pageNumber)
            Result.Success(response.toDomain())
        }
    }


    override suspend fun getMovieDetails(id: Int): Result<MovieGlobal> {
        return returnResult {
            val response = movieApi.getMovieById(id = id)
            Result.Success(response.toDomain())
        }
    }


    override suspend fun getMoviesByGenre(genreId: Int, pageNumber: Int): Result<MoviesList> {
        return returnResult {
            val response = movieApi.getMoviesListByGenre(genreId = genreId, pageNumber = pageNumber)
            Result.Success(response.toDomain())
        }
    }

    override suspend fun getMoviesGenreList(): Result<List<MovieGenre>> {
        return returnResult {
            val response = movieApi.getMoviesGenreList().map { it.toDomain() }
            Result.Success(response)
        }
    }

    /**
     * Local Functions
     */

    override suspend fun removeMovieFromFavourite(movie: MovieGlobal): Result<Boolean> {
        return returnResult {
            movieDao.deleteMovie(movie.toDb())
            Result.Success(true)
        }
    }

    override suspend fun addMovieToFavourite(movie: MovieGlobal): Result<Boolean> {
        return returnResult {
            movieDao.insertMovie(movie.toDb())
            Result.Success(true)
        }
    }

    override suspend fun dropFavouriteMoviesTable(): Result<Boolean> {
        return returnResult {
            movieDao.deleteAllData()
            Result.Success(true)
        }
    }

    override suspend fun undoDroppingFavouriteMovieTable(movieList: List<MovieGlobal>): Result<Boolean> {
        return returnResult {
            movieDao.insertMoviesList(movieList.map { it.toDb() })
            Result.Success(true)
        }
    }

    override suspend fun getFavouriteMoviesList(
        pageNumber: Int,
        offset: Int
    ): Result<List<MovieGlobal>> {
        return returnResult {
            val response = movieDao.getMoviesList(pageNumber, offset)
            Result.Success(response.map { it.toDomain() })
        }
    }

    override suspend fun getAllFavouriteMovies(): Result<List<MovieGlobal>> {
        return returnResult {
            val response = movieDao.getAllFavouriteMovies()
            Result.Success(response.map { it.toDomain() })
        }
    }
}