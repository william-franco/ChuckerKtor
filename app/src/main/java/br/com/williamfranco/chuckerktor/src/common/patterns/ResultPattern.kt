package br.com.williamfranco.chuckerktor.src.common.patterns

sealed class ResultPattern<out S, out E : Exception> {
    data class Success<S>(val value: S) : ResultPattern<S, Nothing>()
    data class Error<E : Exception>(val error: E) : ResultPattern<Nothing, E>()

    inline fun <T> fold(
        onSuccess: (S) -> T,
        onError: (E) -> T,
    ): T = when (this) {
        is Success -> onSuccess(value)
        is Error -> onError(error)
    }
}
