package mo.movies.domain.useCases.local

class LocalUseCases(
    val addMovieToFavouriteUseCase: AddMovieToFavouriteUseCase,
    val dropFavouriteMovieTableUseCase: DropFavouriteMovieTableUseCase,
    val removeMovieFromFavouriteUseCase: RemoveMovieFromFavouriteUseCase,
    val undoDroppingFavouriteMovieTableUseCase: UndoDroppingFavouriteMovieTableUseCase,
    val getFavouriteMoviesListUseCase: GetFavouriteMoviesListUseCase
)