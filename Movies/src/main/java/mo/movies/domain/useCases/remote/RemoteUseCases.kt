package mo.movies.domain.useCases.remote

class RemoteUseCases(
    val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    val getMoviesByCategoryUseCase: GetMoviesByCategoryUseCase,
    val getMoviesByGenreUseCase: GetMoviesByGenreUseCase,
    val getMoviesGenreListUseCase: GetMoviesGenreListUseCase
)