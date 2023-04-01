package fr.bankin.feature.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fr.bankin.feature.presentation.CategoriesList

@Composable
fun  CategoriesScreen(navController: NavHostController = rememberNavController(),
                      startDestination: String = "categories") {

    NavHost(navController = navController, startDestination = startDestination) {
        composable("categories") { CategoriesList(onNavigateToSubCategoriesList = {
            navController.navigate("subcategories/$it")
        }) }
        composable("subcategories/{categoryId}", arguments = listOf(navArgument("categoryId") { type = NavType.IntType }))
        { SubCategoriesList() }
    }
}