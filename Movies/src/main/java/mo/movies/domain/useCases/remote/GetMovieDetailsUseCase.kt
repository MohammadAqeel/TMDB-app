package mo.movies.domain.useCases.remote

import mo.common.Result
import mo.movies.domain.models.MovieGlobal
import mo.movies.domain.repository.MoviesRepository

class GetMovieDetailsUseCase(
    val repository: MoviesRepository
) {

    suspend operator fun invoke(movieId: Int): Result<MovieGlobal> {
        return repository.getMovieDetails(movieId)
    }
}