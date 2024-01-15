package mo.movies.domain.useCases.remote

import mo.common.Result
import mo.movies.domain.models.MovieGenre
import mo.movies.domain.repository.MoviesRepository

class GetMoviesGenreListUseCase(
    val repository: MoviesRepository
) {
    suspend operator fun invoke(): Result<List<MovieGenre>> {
        return repository.getMoviesGenreList()
    }

}