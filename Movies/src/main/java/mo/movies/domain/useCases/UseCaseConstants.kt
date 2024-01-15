package mo.movies.domain.useCases

import mo.movies.domain.models.MovieGlobal

object UseCaseConstants {

    var currentFavouriteList: List<MovieGlobal> = emptyList()

    const val ERROR_MESSAGE_WHEN_DROPPING_TABLE = "Something went wrong please try again later!"
}