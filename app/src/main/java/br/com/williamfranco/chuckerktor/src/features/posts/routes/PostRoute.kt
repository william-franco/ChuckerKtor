package br.com.williamfranco.chuckerktor.src.features.posts.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.williamfranco.chuckerktor.src.features.posts.view_models.PostViewModel
import br.com.williamfranco.chuckerktor.src.features.posts.view_models.PostViewModelImpl
import br.com.williamfranco.chuckerktor.src.features.posts.views.PostView
import org.koin.androidx.compose.koinViewModel

@Composable
fun PostRoute(
    onOpenSettings: () -> Unit,
) {
    val postViewModel: PostViewModel = koinViewModel<PostViewModelImpl>()
    val postState by postViewModel.state.collectAsStateWithLifecycle()

    PostView(
        postState = postState,
        onRefresh = postViewModel::refreshPosts,
        onOpenSettings = onOpenSettings,
    )
}
