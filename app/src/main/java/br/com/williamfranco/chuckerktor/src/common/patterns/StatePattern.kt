package br.com.williamfranco.chuckerktor.src.common.patterns

sealed class StatePattern<out S, out E : Exception> {
    data object Initial : StatePattern<Nothing, Nothing>()
    data object Loading : StatePattern<Nothing, Nothing>()
    data class Success<S>(val data: S) : StatePattern<S, Nothing>()
    data class Error<E : Exception>(val error: E) : StatePattern<Nothing, E>()
}
