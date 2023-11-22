package br.com.williamfranco.chuckerktor.src.dependency_injector

import android.content.Context

import br.com.williamfranco.chuckerktor.src.features.posts.repositories.*
import br.com.williamfranco.chuckerktor.src.features.posts.view_models.*
import br.com.williamfranco.chuckerktor.src.features.settings.view_models.*
import br.com.williamfranco.chuckerktor.src.services.*

class DependencyInjector(context: Context) {
    // Services
    private val httpService = HttpService(context)
    // Repositories
    private val postRepository: PostRepository = PostRepositoryImpl(httpService)
    // ViewModels
    val postViewModel: PostViewModel = PostViewModelImpl(postRepository)
    val settingViewModel: SettingViewModel = SettingViewModelImpl()
}
