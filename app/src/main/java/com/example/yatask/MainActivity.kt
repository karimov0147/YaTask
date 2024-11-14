package com.example.yatask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.yatask.ui.screens.homeScreen.HomeScreen
import com.example.yatask.ui.screens.homeScreen.HomeScreenRoute
import com.example.yatask.ui.screens.noteInfoScreen.NoteInfoScreenRoute
import com.example.yatask.ui.screens.noteInfoScreen.NoteScreen
import com.example.yatask.ui.theme.YaTaskTheme
import com.example.yatask.utils.NavigationPath
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YaTaskTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = NavigationPath.HOME_SCREEN.name
                )
                {
                    composable(NavigationPath.HOME_SCREEN.name) {
                        HomeScreenRoute(
                            navigateToEditScreen =  {navController.navigate(NavigationPath.NOTE_SCREEN.name + "/{$it}")}
                        )
                    }
                    composable(
                        route = NavigationPath.NOTE_SCREEN.name + "/{id}" ,
                        arguments = listOf(navArgument("id")
                        { type = NavType.StringType } )
                    ) {
                        val arg = it.arguments?.getString("id")
                        NoteInfoScreenRoute( navArgs = arg  , onBackClicked = {navController.navigateUp()})
                    }
                }
            }
        }
    }
}