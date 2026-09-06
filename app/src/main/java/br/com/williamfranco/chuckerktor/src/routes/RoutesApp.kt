package br.com.williamfranco.chuckerktor.src.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.williamfranco.chuckerktor.src.features.posts.routes.PostRoute
import br.com.williamfranco.chuckerktor.src.features.settings.routes.SettingRoute

@Composable
fun RoutesApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.POSTS,
    ) {
        composable(Routes.POSTS) {
            PostRoute(
                onOpenSettings = { navController.navigate(Routes.SETTINGS) },
            )
        }

        composable(Routes.SETTINGS) {
            SettingRoute(onBack = navController::popBackStack)
        }
    }
}
