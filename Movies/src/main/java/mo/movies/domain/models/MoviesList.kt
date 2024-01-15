package mo.movies.domain.models

class MoviesList(
    val page: Int,
    val results: List<MovieGlobal>,
    val totalPages: Int,
    val totalResults: Int
) {
    fun hasNextPage(currentPage: Int): Boolean = currentPage < totalPages
}

