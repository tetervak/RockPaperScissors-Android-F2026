package ca.tetervak.rockpaperscissors.ui.play

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.tetervak.rockpaperscissors.R
import ca.tetervak.rockpaperscissors.domain.Choice
import ca.tetervak.rockpaperscissors.ui.common.ChoiceToImage
import ca.tetervak.rockpaperscissors.ui.common.GameTopBar
import ca.tetervak.rockpaperscissors.ui.common.choiceToString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayScreen(
    userChoice: Choice,
    onUserChoiceChange: (Choice) -> Unit,
    onPlay: () -> Unit,
    onHelpButtonClick: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            GameTopBar(
                title = stringResource(R.string.play_the_game),
                onHelpButtonClick = onHelpButtonClick,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            if (userChoice != Choice.UNKNOWN) {
                FloatingActionButton(
                    onClick = onPlay
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = stringResource(R.string.play)
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(32.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.make_your_choice),
                fontSize = 32.sp,
                color = colorResource(id = R.color.green_800)
            )
            UserChoiceInput(
                userChoice = userChoice,
                onChange = onUserChoiceChange
            )
            Button(
                onClick = onPlay,
                enabled = userChoice != Choice.UNKNOWN
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.play),
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun SelectOption(choiceOption: Choice, selected: Boolean, onClick: () -> Unit) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            if (selected) 16.dp else 4.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            ChoiceToImage(
                choice = choiceOption,
                modifier = Modifier.border(
                    width = 2.dp,
                    shape = CircleShape,
                    color = if (selected) MaterialTheme.colorScheme.primary else Color.Gray
                )
            )
            RadioButton(
                selected = selected,
                onClick = null
            )
            Text(
                text = choiceToString(choice = choiceOption),
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun UserChoiceInput(
    userChoice: Choice,
    onChange: (Choice) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        SelectOption(
            choiceOption = Choice.ROCK,
            selected = userChoice == Choice.ROCK,
            onClick = { onChange(Choice.ROCK) }
        )
        SelectOption(
            choiceOption = Choice.PAPER,
            selected = userChoice == Choice.PAPER,
            onClick = { onChange(Choice.PAPER) }
        )
        SelectOption(
            choiceOption = Choice.SCISSORS,
            selected = userChoice == Choice.SCISSORS,
            onClick = { onChange(Choice.SCISSORS) }
        )
    }
}
