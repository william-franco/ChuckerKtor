package br.com.williamfranco.chuckerktor.src.features.settings.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.williamfranco.chuckerktor.src.features.settings.models.SettingModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingView(
    setting: SettingModel,
    onBack: () -> Unit,
    onThemeChanged: (Boolean) -> Unit,
) {
    var showAboutDialog by remember { mutableStateOf(false) }

    if (showAboutDialog) {
        AlertDialog(
            onDismissRequest = { showAboutDialog = false },
            title = { Text("Chucker Ktor") },
            text = {
                Column {
                    Text("Version 1.0.0")
                    Text("© 2026 William Franco")
                }
            },
            confirmButton = {
                TextButton(onClick = { showAboutDialog = false }) {
                    Text("OK")
                }
            },
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            ListItem(
                headlineContent = { Text("Dark theme") },
                trailingContent = {
                    Switch(
                        checked = setting.isDarkTheme,
                        onCheckedChange = onThemeChanged,
                    )
                },
            )
            ListItem(
                headlineContent = { Text("About") },
                leadingContent = {
                    Icon(Icons.Outlined.Info, contentDescription = null)
                },
                modifier = Modifier.clickable { showAboutDialog = true },
            )
        }
    }
}
