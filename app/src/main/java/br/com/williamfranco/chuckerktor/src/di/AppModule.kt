package br.com.williamfranco.chuckerktor.src.di

import br.com.williamfranco.chuckerktor.src.common.services.HttpService
import br.com.williamfranco.chuckerktor.src.features.posts.repositories.PostRepository
import br.com.williamfranco.chuckerktor.src.features.posts.repositories.PostRepositoryImpl
import br.com.williamfranco.chuckerktor.src.features.posts.view_models.PostViewModel
import br.com.williamfranco.chuckerktor.src.features.posts.view_models.PostViewModelImpl
import br.com.williamfranco.chuckerktor.src.features.settings.repositories.SettingRepository
import br.com.williamfranco.chuckerktor.src.features.settings.repositories.SettingRepositoryImpl
import br.com.williamfranco.chuckerktor.src.features.settings.repositories.settingsDataStore
import br.com.williamfranco.chuckerktor.src.features.settings.view_models.SettingViewModel
import br.com.williamfranco.chuckerktor.src.features.settings.view_models.SettingViewModelImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { HttpService(androidContext()) }
    single<PostRepository> { PostRepositoryImpl(get()) }
    single { androidContext().settingsDataStore }
    single<SettingRepository> { SettingRepositoryImpl(get()) }
    viewModelOf(::PostViewModelImpl) { bind<PostViewModel>() }
    viewModelOf(::SettingViewModelImpl) { bind<SettingViewModel>() }
}
