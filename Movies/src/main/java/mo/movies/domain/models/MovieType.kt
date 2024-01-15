package mo.movies.domain.models

sealed class MovieType {

    class Movie(val genreIds: List<Int>) : MovieType()

    class MovieDetails(val genres: List<MovieGenre>, val runTime: Int) : MovieType() {
        fun getWatchTime(): String {
            val hours = runTime / 60
            val minutes = runTime % 60
            return "$hours:$minutes"
        }
    }
}