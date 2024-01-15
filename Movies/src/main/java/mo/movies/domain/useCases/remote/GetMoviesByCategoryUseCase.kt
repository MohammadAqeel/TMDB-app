package mo.movies.domain.useCases.remote

import mo.common.Result
import mo.movies.domain.models.MoviesList
import mo.movies.domain.repository.MoviesRepository

class GetMoviesByCategoryUseCase(
    val repository: MoviesRepository
) {

    suspend operator fun invoke(category: String, pageNumber: Int): Result<MoviesList> {
        return repository.getMoviesByCategory(category, pageNumber)
    }

}