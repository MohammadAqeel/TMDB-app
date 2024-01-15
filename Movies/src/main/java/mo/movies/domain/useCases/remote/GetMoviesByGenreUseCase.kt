package mo.movies.domain.useCases.remote

import mo.common.Result
import mo.movies.domain.models.MoviesList
import mo.movies.domain.repository.MoviesRepository

class GetMoviesByGenreUseCase(
    val repository: MoviesRepository
) {
    suspend operator fun invoke(genreId: Int, pageNumber: Int): Result<MoviesList> {
        return repository.getMoviesByGenre(genreId, pageNumber)
    }

}