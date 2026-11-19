package com.example.playlist_maker_android_mikhaylovaangelina

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.playlist_maker_android_mikhaylovaangelina.ui.MainScreen

// 1. Enum-класс с перечислением имеющихся экранов
enum class PlaylistScreen {
    MAIN,
    SEARCH,
    SETTINGS
}

// 2. Composable-функция PlaylistHost, принимающая NavController
@Composable
fun PlaylistHost(
    navController: NavHostController
) {
    // 4. Методы для перехода на экраны с использованием NavController и enum-класса
    val navigateToSearch: () -> Unit = {
        navController.navigate(PlaylistScreen.SEARCH.name) {
            launchSingleTop = true
        }
    }

    val navigateToSettings: () -> Unit = {
        navController.navigate(PlaylistScreen.SETTINGS.name) {
            launchSingleTop = true
        }
    }

    val navigateBack: () -> Unit = {
        navController.popBackStack()
    }

    // 3. NavHost, в котором задаются все экраны и передаются методы переходов
    NavHost(
        navController = navController,
        startDestination = PlaylistScreen.MAIN.name
    ) {
        composable(route = PlaylistScreen.MAIN.name) {
            MainScreen(
                onSearchClick = navigateToSearch,
                onSettingsClick = navigateToSettings
            )
        }

        composable(route = PlaylistScreen.SEARCH.name) {
            SearchScreen(
                onBackClick = navigateBack
            )
        }

        composable(route = PlaylistScreen.SETTINGS.name) {
            SettingsScreen(
                onBackClick = navigateBack
            )
        }
    }
}
