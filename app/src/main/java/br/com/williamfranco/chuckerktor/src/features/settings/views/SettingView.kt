package br.com.williamfranco.chuckerktor.src.features.settings.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.*
import androidx.navigation.NavController

import br.com.williamfranco.chuckerktor.src.features.settings.view_models.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingView(navController: NavController, settingViewModel: SettingViewModel) {
    val settingState by settingViewModel.isDarkTheme.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Settings")
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = {padding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(padding)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Dark theme", color = Color.Black)
                    Spacer(modifier = Modifier.weight(1f))
                    Switch(
                        checked = settingState.isDark,
                        onCheckedChange = { isChecked -> settingViewModel.updateTheme(isChecked) }
                    )
                }
            }
        }
    )
}
