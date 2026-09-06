package br.com.williamfranco.chuckerktor.src.features.posts.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.williamfranco.chuckerktor.src.common.patterns.StatePattern
import br.com.williamfranco.chuckerktor.src.features.posts.exceptions.PostException
import br.com.williamfranco.chuckerktor.src.features.posts.models.PostModel
import br.com.williamfranco.chuckerktor.src.features.posts.repositories.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

typealias PostsState = StatePattern<List<PostModel>, PostException>

interface PostViewModel {
    val state: StateFlow<PostsState>
    fun refreshPosts()
}

class PostViewModelImpl(
    private val postRepository: PostRepository,
) : ViewModel(), PostViewModel {

    private val _state = MutableStateFlow<PostsState>(StatePattern.Initial)
    override val state: StateFlow<PostsState> = _state.asStateFlow()

    init {
        refreshPosts()
    }

    override fun refreshPosts() {
        viewModelScope.launch {
            _state.value = StatePattern.Loading

            val nextState = postRepository.getPosts().fold(
                onSuccess = { posts -> StatePattern.Success(posts) },
                onError = { error -> StatePattern.Error(error) },
            )

            _state.value = nextState
        }
    }
}
