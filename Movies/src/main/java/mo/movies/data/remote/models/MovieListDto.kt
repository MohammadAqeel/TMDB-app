package mo.movies.data.remote.models

import com.google.gson.annotations.SerializedName
import mo.movies.domain.models.MoviesList

data class MovieListDto(
    val page: Int,
    val results: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
    fun toDomain(): MoviesList =
        MoviesList(
            page = page,
            results = results.map { it.toDomain() },
            totalPages = totalPages,
            totalResults = totalResults
        )
}


