package mo.movies.data.remote.models.movieDetails

import com.google.gson.annotations.SerializedName

data class BelongsToCollectionDto(
    @SerializedName("backdrop_path") val backdropPath: String,
    val id: Int,
    val name: String,
    @SerializedName("poster_path") val posterPath: String
)