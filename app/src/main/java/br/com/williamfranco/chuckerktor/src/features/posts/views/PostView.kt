package br.com.williamfranco.chuckerktor.src.features.posts.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import br.com.williamfranco.chuckerktor.src.features.posts.models.*
import br.com.williamfranco.chuckerktor.src.features.posts.view_models.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostView(navController: NavController, viewModel: PostViewModel) {
    val postsState by viewModel.posts.collectAsState()
    val isLoadingState by viewModel.isLoading.collectAsState()

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
fun PostList(posts: List<PostModel>) {
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
