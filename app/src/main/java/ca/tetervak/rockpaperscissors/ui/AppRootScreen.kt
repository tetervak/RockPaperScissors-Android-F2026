package ca.tetervak.rockpaperscissors.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import ca.tetervak.rockpaperscissors.ui.common.AboutDialog
import ca.tetervak.rockpaperscissors.ui.play.PlayScreen
import ca.tetervak.rockpaperscissors.ui.result.ResultScreen
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer


@Serializable
sealed interface GameNavKey : NavKey

@Serializable
data object Play : GameNavKey

@Serializable
data object Result : GameNavKey

@Composable
fun AppRootScreen(viewModel: GameViewModel = viewModel()) {

    val uiState: GameUiState by viewModel.gameUiState.collectAsState()

    val gameNavBackStack = rememberSerializable(serializer = serializer()) {
        NavBackStack<GameNavKey>(Play)
    }

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

