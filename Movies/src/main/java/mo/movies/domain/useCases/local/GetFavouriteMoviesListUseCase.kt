package mo.movies.domain.useCases.local

import mo.common.Result
import mo.movies.domain.models.MovieGlobal
import mo.movies.domain.repository.MoviesRepository

class GetFavouriteMoviesListUseCase(
    val repository: MoviesRepository
) {
    suspend operator fun invoke(pageNumber: Int, offset: Int): Result<List<MovieGlobal>> {
        return repository.getFavouriteMoviesList(pageNumber, offset)
    }


}