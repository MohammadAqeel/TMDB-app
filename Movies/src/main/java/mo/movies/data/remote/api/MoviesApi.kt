package mo.movies.data.remote.api

import mo.common.RemoteConstants.API_KEY
import mo.movies.data.remote.models.MovieListDto
import mo.movies.data.remote.models.movieDetails.GenreDto
import mo.movies.data.remote.models.movieDetails.MovieDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    /**
     * get popular movies
     * https://api.themoviedb.org/3/movie/popular?api_key=9d6c757de517ea048d34945ace81a45d
     */

    @GET("/3/movie/{category}?")
    suspend fun getMoviesListByCategory(
        @Path("category") category: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") pageNumber: Int = 1
    ): MovieListDto

    /**
     * get movie details
     * https://api.themoviedb.org/3/movie/343611?api_key=9d6c757de517ea048d34945ace81a45d
     */

    @GET("/3/movie/{movieId}?")
    suspend fun getMovieById(
        @Path("movieId") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): MovieDetailsDto

    /**
     * get movies genre list
     * https://api.themoviedb.org/3/genre/movie/list?api_key=9d6c757de517ea048d34945ace81a45d
     */

    @GET("/3/genre/movie/list?")
    suspend fun getMoviesGenreList(
        @Query("api_key") apiKey: String = API_KEY
    ): List<GenreDto>

    /**
     * get movies List by genre
     * https://api.themoviedb.org/3/discover/movie?api_key=YOUR_API_KEY&with_genres=GENRE_ID
     *
     */

    @GET("/3/discover/movie?")
    suspend fun getMoviesListByGenre(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("with_genres") genreId: Int,
        @Query("page") pageNumber: Int = 1
    ): MovieListDto

}