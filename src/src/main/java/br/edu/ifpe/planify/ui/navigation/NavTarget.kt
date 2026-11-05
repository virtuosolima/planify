package br.edu.ifpe.planify.ui.navigation

sealed class NavTarget(val route: String) {
    object Home : NavTarget("home")
}