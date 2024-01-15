package mo.movies.data.remote.models.movieDetails

import mo.movies.domain.models.MovieGenre

data class GenreDto(
    val id: Int,
    val name: String
) {
    fun toDomain(): MovieGenre {
        return MovieGenre(
            id = id,
            name = name
        )
    }
}
