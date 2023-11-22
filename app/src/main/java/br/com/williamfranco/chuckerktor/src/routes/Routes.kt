package br.com.williamfranco.chuckerktor.src.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*

import br.com.williamfranco.chuckerktor.src.dependency_injector.*
import br.com.williamfranco.chuckerktor.src.features.posts.views.PostView
import br.com.williamfranco.chuckerktor.src.features.settings.views.SettingView

@Composable
fun RoutesApp(dependencies: DependencyInjector) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "postsView"
    ) {
        composable("postsView") {
            PostView(navController, dependencies.postViewModel)
        }
        composable("settingView") {
            SettingView(navController, dependencies.settingViewModel)
        }
    }
}
