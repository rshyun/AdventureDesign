package com.example.myadv.core

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myadv.data.PuzzleImageData
import com.example.myadv.ui.screen.ExplainHomeScreen
import com.example.myadv.ui.screen.ExplainScreen
import com.example.myadv.ui.screen.GreetingScreen
import com.example.myadv.ui.screen.HomeScreen
import com.example.myadv.ui.screen.NamingScreen
import com.example.myadv.ui.screen.PuzzleBookScreen
import com.example.myadv.ui.screen.SpaceshipShopScreen
import com.example.myadv.data.isFirstLaunch
import com.example.myadv.model.PuzzleViewModel
import com.example.myadv.ui.screen.ClearScreen
import com.example.myadv.ui.screen.HintScreen
import com.example.myadv.ui.screen.HomeSettingScreen
import com.example.myadv.ui.screen.NameChangeScreen
import com.example.myadv.ui.screen.PuzzleScreen
import com.example.myadv.ui.screen.PuzzleSettingScreen
import com.example.myadv.ui.screen.QuizFalseScreen
import com.example.myadv.ui.screen.QuizScreen
import com.example.myadv.ui.screen.QuizClearScreen
import com.example.myadv.ui.screen.QuokkaScreen

@Composable
fun AppNavigator(context: Context) {
    val navController: NavHostController = rememberNavController()

    // 최초 실행 여부 확인
    val startDestination = if (context.isFirstLaunch()) "greeting_screen" else "home_screen"

    NavHost(navController = navController, startDestination = startDestination) {
        composable("greeting_screen") { GreetingScreen(navController = navController) }
        composable("naming_screen") { NamingScreen(navController = navController, context = context) }
        composable("home_screen") { HomeScreen(navController = navController) }
        composable("spaceship_shop_screen") { SpaceshipShopScreen(navController = navController) }
        composable("puzzle_book_screen") { PuzzleBookScreen(navController = navController) }
        composable("explain_home_screen") { ExplainHomeScreen(navController = navController) }
        composable("explain_screen") { ExplainScreen(navController = navController) }
        composable("quiz_screen") { QuizScreen(navController = navController) }
        composable("true_screen") { QuizClearScreen(navController = navController) }
        composable("false_screen") { QuizFalseScreen(navController = navController) }
        composable("home_setting_screen") { HomeSettingScreen(navController = navController) }
        composable("puzzle_setting_screen") { PuzzleSettingScreen(navController = navController) }
        composable("hint_screen") { HintScreen(navController = navController) }
        composable("quokka_screen") { QuokkaScreen(navController = navController) }
        composable("name_change_screen") { NameChangeScreen(navController = navController, context = context) }
        composable("clear_screen") { ClearScreen(navController = navController) }
        composable("puzzle_screen/{puzzleId}") { backStackEntry ->
            val puzzleId = backStackEntry.arguments?.getString("puzzleId")?.toInt() ?: 1
            val puzzleData = PuzzleImageData.getPuzzleById(puzzleId)
            val context = LocalContext.current
            val originalBitmap = BitmapFactory.decodeResource(
                context.resources,
                puzzleData.unclearImageRes
            )
            val puzzleViewModel = PuzzleViewModel(
                puzzleImage = puzzleData,
                originalBitmap = originalBitmap
            )

            PuzzleScreen(
                navController = navController,
                puzzleViewModel = puzzleViewModel,
                puzzleId = 1
            )
        }
    }
}