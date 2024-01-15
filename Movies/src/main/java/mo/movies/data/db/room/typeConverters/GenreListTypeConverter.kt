package mo.movies.data.db.room.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mo.movies.domain.models.MovieGenre

class GenreListTypeConverter {
    @TypeConverter
    fun fromGenresList(genres: List<MovieGenre>): String {
        val gson = Gson()
        return gson.toJson(genres)
    }

    @TypeConverter
    fun toGenresList(genresString: String): List<MovieGenre> {
        val gson = Gson()
        val type = object : TypeToken<List<MovieGenre>>() {}.type
        return gson.fromJson(genresString, type)
    }

}