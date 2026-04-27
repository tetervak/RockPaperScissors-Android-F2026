package ca.tetervak.rockpaperscissors.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.tetervak.rockpaperscissors.R
import ca.tetervak.rockpaperscissors.domain.Choice
import ca.tetervak.rockpaperscissors.domain.GameResult

@Composable
fun choiceToString(choice: Choice): String =
    when (choice) {
        Choice.PAPER -> stringResource(id = R.string.paper)
        Choice.ROCK -> stringResource(id = R.string.rock)
        Choice.SCISSORS -> stringResource(id = R.string.scissors)
        else -> stringResource(R.string.unknown)
    }

@Composable
fun resultToString(gameResult: GameResult): String =
    when (gameResult) {
        GameResult.USER_WINS -> stringResource(id = R.string.you_win)
        GameResult.COMPUTER_WINS -> stringResource(id = R.string.computer_wins)
        GameResult.REPLAY -> stringResource(id = R.string.play_again)
        else -> stringResource(R.string.unknown_result)
    }

@Composable
fun ChoiceToImage(choice: Choice, modifier: Modifier = Modifier) = Image(
    painter = painterResource(choiceImageResourceId(choice)),
    contentDescription = choiceToString(choice = choice),
    modifier = modifier.size(60.dp).clip(CircleShape)
)

private fun choiceImageResourceId(choice: Choice) = when(choice){
    Choice.ROCK -> R.drawable.rock
    Choice.PAPER -> R.drawable.paper
    Choice.SCISSORS -> R.drawable.scissors
    else -> R.drawable.baseline_help_outline_24
}
