package br.com.williamfranco.chuckerktor.src.features.posts.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.*
import androidx.navigation.NavController

import br.com.williamfranco.chuckerktor.src.features.posts.models.*
import br.com.williamfranco.chuckerktor.src.features.posts.view_models.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostView(navController: NavController, viewModel: PostViewModel) {
    val postsState by viewModel.posts.collectAsStateWithLifecycle()
    val isLoadingState by viewModel.isLoading.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Posts")
                },
                actions = {
                    IconButton(
                        onClick = { viewModel.refreshPosts() }
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                    IconButton(
                        onClick = { navController.navigate("settingView") }
                    ) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        },
        content = {padding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                if (isLoadingState) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(CenterHorizontally)
                    )
                } else {
                    PostList(postsState)
                }
            }
        },
    )
}

@Composable
private fun PostList(posts: List<PostModel>) {
    LazyColumn {
        items(posts) { post ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 18.dp)
            ) {
                Text(text = "Title: ${post.title}", fontWeight = FontWeight.Bold)
                Text(text = "Description: ${post.body}")
                Divider(modifier = Modifier.padding(vertical = 2.dp))
            }
        }
    }
}
