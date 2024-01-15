package mo.movies.data.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import mo.movies.data.db.models.MovieDb
import mo.movies.data.db.room.dao.MovieDao
import mo.movies.data.db.room.typeConverters.GenreListTypeConverter
import mo.movies.data.db.util.DbConstants.DATABASE_NAME

@Database(entities = [MovieDb::class], version = 1)
@TypeConverters(GenreListTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao

    companion object {
        @Volatile
        private var databaseInstance: MovieDatabase? = null
        fun getDatabaseInstance(context: Context): MovieDatabase {
            return databaseInstance ?: synchronized(this) {
                databaseInstance ?: createDatabase(context).also { databaseInstance = it }
            }
        }

        private fun createDatabase(context: Context): MovieDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java, name = DATABASE_NAME
            ).build()
        }
    }

}