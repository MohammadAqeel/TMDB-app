package mo.movies.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import mo.movies.data.db.util.DbConstants.MOVIE_TABLE_NAME
import mo.movies.domain.models.MovieGenre
import mo.movies.domain.models.MovieGlobal
import mo.movies.domain.models.MovieType

@Entity(tableName = MOVIE_TABLE_NAME)
class MovieDb(
    private val adult: Boolean,
    private val backdropPath: String,
    private val genres: List<MovieGenre>,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    private val originalLanguage: String,
    private val originalTitle: String,
    private val overview: String,
    private val popularity: Double,
    private val posterPath: String,
    private val releaseDate: String,
    private val runTime: Int,
    private val title: String,
    private val video: Boolean,
    private val voteAverage: Double,
    private val voteCount: Int
) {
    fun toDomain(): MovieGlobal {
        return MovieGlobal(
            adult = adult,
            backdropPath = backdropPath,
            id = id,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount,
            type = MovieType.MovieDetails(genres, runTime)
        )
    }
}

fun MovieGlobal.toDb(): MovieDb {
    return when (type) {
        is MovieType.MovieDetails -> {
            MovieDb(
                adult,
                backdropPath,
                type.genres,
                id,
                originalLanguage,
                originalTitle,
                overview,
                popularity,
                posterPath,
                releaseDate,
                type.runTime,
                title,
                video,
                voteAverage,
                voteCount,
            )
        }
        /**
         * this case does not exist but has been added only to make the toDb function return non null data
         */
        is MovieType.Movie -> {
            MovieDb(
                adult,
                backdropPath,
                getGenresList(type.genreIds),
                id,
                originalLanguage,
                originalTitle,
                overview,
                popularity,
                posterPath,
                releaseDate,
                0,
                title,
                video,
                voteAverage,
                voteCount
            )
        }
    }
}


/**
 * this function is not going to be called due to saving the movie from details page which will be sending the details object
 */

fun getGenresList(genreIds: List<Int>): List<MovieGenre> {
    val genresList =
        emptyList<MovieGenre>() // add a global list and fetch it on splash then use it here
    val newList = mutableListOf<MovieGenre>()
    genreIds.forEach { genreId ->
        val currentGenre = genresList.find { listGenreId -> listGenreId.id == genreId }
        if (currentGenre != null)
            newList.add(currentGenre)
    }
    return newList
}


