package ca.tetervak.rockpaperscissors.ui

import ca.tetervak.rockpaperscissors.domain.Choice
import ca.tetervak.rockpaperscissors.domain.GameResult

data class GameUiState(
    val userChoice: Choice = Choice.UNKNOWN,
    val computerChoice: Choice = Choice.UNKNOWN,
    val gameResult: GameResult = GameResult.UNKNOWN
)