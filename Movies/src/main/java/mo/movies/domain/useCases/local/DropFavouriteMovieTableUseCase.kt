package mo.movies.domain.useCases.local

import mo.common.Result
import mo.movies.domain.repository.MoviesRepository
import mo.movies.domain.useCases.UseCaseConstants
import mo.movies.domain.useCases.UseCaseConstants.ERROR_MESSAGE_WHEN_DROPPING_TABLE

class DropFavouriteMovieTableUseCase(
    val repository: MoviesRepository
) {
    suspend operator fun invoke(): Result<Boolean> {
        // get the current list so that it can be restored then.
        when (val response = repository.getAllFavouriteMovies()) {
            is Result.Error -> return Result.Error(ERROR_MESSAGE_WHEN_DROPPING_TABLE)
            is Result.Success -> UseCaseConstants.currentFavouriteList =
                response.data ?: emptyList()
        }
        return repository.dropFavouriteMoviesTable()
    }
}