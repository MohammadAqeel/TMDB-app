package mo.movies.data.remote.models.movieDetails

import com.google.gson.annotations.SerializedName

data class ProductionCompanyDto(
    val id: Int,
    @SerializedName("logo_path") val logoPath: String,
    val name: String,
    @SerializedName("origin_country") val originCountry: String
)