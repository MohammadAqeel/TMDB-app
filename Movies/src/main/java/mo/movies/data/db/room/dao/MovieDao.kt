package mo.movies.data.db.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mo.movies.data.db.models.MovieDb
import mo.movies.data.db.util.DbConstants.MOVIE_TABLE_NAME

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieDb: MovieDb): Int

    @Delete
    suspend fun deleteMovie(movieDb: MovieDb): Boolean

    @Query("SELECT * FROM $MOVIE_TABLE_NAME LIMIT :pageSize OFFSET :offset")
    suspend fun getMoviesList(pageSize: Int, offset: Int): List<MovieDb>

    @Query("DELETE FROM $MOVIE_TABLE_NAME")
    suspend fun deleteAllData(): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviesList(movie: List<MovieDb>)


    @Query("SELECT * FROM $MOVIE_TABLE_NAME")
    suspend fun getAllFavouriteMovies(): List<MovieDb>

}