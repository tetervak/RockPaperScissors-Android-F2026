package ca.tetervak.rockpaperscissors.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ca.tetervak.rockpaperscissors.ui.common.AboutDialog
import ca.tetervak.rockpaperscissors.ui.play.PlayScreen
import ca.tetervak.rockpaperscissors.ui.result.ResultScreen

@Composable
fun AppRootScreen(viewModel: GameViewModel = viewModel()) {

    val uiState: GameUiState by viewModel.gameUiState.collectAsState()

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "play"
    ){
        composable(route = "play") {
            PlayScreen(
                userChoice = uiState.userChoice,
                onUserChoiceChange = viewModel::onUserChoiceChange,
                onPlay = {
                    viewModel.onPlay()
                    navController.navigate(route = "result")
                },
                onHelpButtonClick = viewModel::onOpenHelp
            )
        }
        composable(route = "result") {
            ResultScreen(
                userChoice = uiState.userChoice,
                computerChoice = uiState.computerChoice,
                gameResult = uiState.gameResult,
                onReplay = {
                    navController.popBackStack()
                },
                onHelpButtonClick = viewModel::onOpenHelp
            )
        }
    }

    if(viewModel.showHelp){
        AboutDialog(onDismissRequest = viewModel::onCloseHelp)
    }
}

