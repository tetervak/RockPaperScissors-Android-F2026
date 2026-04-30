package ca.tetervak.rockpaperscissors.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import ca.tetervak.rockpaperscissors.ui.common.AboutDialog
import ca.tetervak.rockpaperscissors.ui.play.PlayScreen
import ca.tetervak.rockpaperscissors.ui.result.ResultScreen
import kotlinx.serialization.Serializable


@Serializable
data object Play : NavKey

@Serializable
data object Result : NavKey

@Composable
fun AppRootScreen(viewModel: GameViewModel = viewModel()) {

    val uiState: GameUiState by viewModel.gameUiState.collectAsState()

    val gameNavBackStack = rememberNavBackStack(Play)

    NavDisplay(
        backStack = gameNavBackStack,
        onBack = { gameNavBackStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Play> {
                PlayScreen(
                    userChoice = uiState.userChoice,
                    onUserChoiceChange = viewModel::onUserChoiceChange,
                    onPlay = {
                        viewModel.onPlay()
                        gameNavBackStack.add(Result)
                    },
                    onHelpButtonClick = viewModel::onOpenHelp
                )
            }
            entry<Result> {
                ResultScreen(
                    userChoice = uiState.userChoice,
                    computerChoice = uiState.computerChoice,
                    gameResult = uiState.gameResult,
                    onReplay = {
                        gameNavBackStack.removeLastOrNull()
                    },
                    onHelpButtonClick = viewModel::onOpenHelp
                )
            }
        }
    )

    if (viewModel.showHelp) {
        AboutDialog(onDismissRequest = viewModel::onCloseHelp)
    }
}

