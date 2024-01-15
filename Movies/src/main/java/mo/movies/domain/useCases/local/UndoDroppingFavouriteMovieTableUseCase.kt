package mo.movies.domain.useCases.local

import mo.movies.domain.repository.MoviesRepository
import mo.movies.domain.useCases.UseCaseConstants

class UndoDroppingFavouriteMovieTableUseCase(
    val repository: MoviesRepository
) {

    suspend operator fun invoke() {
        repository.undoDroppingFavouriteMovieTable(UseCaseConstants.currentFavouriteList)
    }
}