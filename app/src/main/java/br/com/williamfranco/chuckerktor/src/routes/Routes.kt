package br.com.williamfranco.chuckerktor.src.routes

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import br.com.williamfranco.chuckerktor.src.features.posts.repositories.*
import br.com.williamfranco.chuckerktor.src.features.posts.view_models.*
import br.com.williamfranco.chuckerktor.src.features.posts.views.PostView

import br.com.williamfranco.chuckerktor.src.features.settings.view_models.*
import br.com.williamfranco.chuckerktor.src.features.settings.views.SettingView

import br.com.williamfranco.chuckerktor.src.services.HttpService

@Composable
fun RoutesApp(context: Context) {
    val navController = rememberNavController()

    val httpService = HttpService(context)
    val postRepository : PostRepository = PostRepositoryImpl(httpService)
    val postViewModel : PostViewModel = PostViewModelImpl(postRepository)
    val settingViewModel: SettingViewModel = SettingViewModelImpl()

    NavHost(
        navController = navController,
        startDestination = "postsView"
    ) {
        composable("postsView") {
            PostView(navController, postViewModel)
        }
        composable("settingView") {
            SettingView(navController, settingViewModel)
        }
    }
}
