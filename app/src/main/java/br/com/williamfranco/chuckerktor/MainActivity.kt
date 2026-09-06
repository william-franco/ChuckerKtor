package br.com.williamfranco.chuckerktor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.williamfranco.chuckerktor.src.design.theme.ChuckerKtorTheme
import br.com.williamfranco.chuckerktor.src.features.settings.view_models.SettingViewModel
import br.com.williamfranco.chuckerktor.src.features.settings.view_models.SettingViewModelImpl
import br.com.williamfranco.chuckerktor.src.routes.RoutesApp
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val settingViewModel: SettingViewModel = koinViewModel<SettingViewModelImpl>(
                viewModelStoreOwner = this,
            )
            val setting by settingViewModel.state.collectAsStateWithLifecycle()

            ChuckerKtorTheme(darkTheme = setting.isDarkTheme) {
                RoutesApp()
            }
        }
    }
}
