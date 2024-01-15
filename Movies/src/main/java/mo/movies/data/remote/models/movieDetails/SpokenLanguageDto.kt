package mo.movies.data.remote.models.movieDetails

import com.google.gson.annotations.SerializedName

data class SpokenLanguageDto(
    @SerializedName("english_name") val englishName: String,
    val iso_639_1: String,
    val name: String
)