package mo.movies.domain.useCases.local

import mo.common.Result
import mo.movies.domain.models.MovieGlobal
import mo.movies.domain.repository.MoviesRepository

class AddMovieToFavouriteUseCase(
    val repository: MoviesRepository
) {

    suspend operator fun invoke(movie: MovieGlobal): Result<Boolean> {
        return repository.addMovieToFavourite(movie)
    }


}