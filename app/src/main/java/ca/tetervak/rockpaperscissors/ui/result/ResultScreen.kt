package ca.tetervak.rockpaperscissors.ui.result

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
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.tetervak.rockpaperscissors.R
import ca.tetervak.rockpaperscissors.domain.Choice
import ca.tetervak.rockpaperscissors.domain.GameResult
import ca.tetervak.rockpaperscissors.ui.common.ChoiceToImage
import ca.tetervak.rockpaperscissors.ui.common.GameTopBar
import ca.tetervak.rockpaperscissors.ui.common.choiceToString
import ca.tetervak.rockpaperscissors.ui.common.resultToString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    userChoice: Choice,
    computerChoice: Choice,
    gameResult: GameResult,
    onReplay: () -> Unit,
    onHelpButtonClick: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            GameTopBar(
                title = stringResource(id = R.string.game_result),
                onHelpButtonClick = onHelpButtonClick,
                scrollBehavior = scrollBehavior,
                onNavigateBack = onReplay
            )
        },
        floatingActionButton = {
            if(userChoice != Choice.UNKNOWN){
                FloatingActionButton(
                    onClick = onReplay
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = stringResource(R.string.replay)
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .verticalScroll(rememberScrollState())
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = resultToString(gameResult = gameResult),
                fontSize = 32.sp,
                color = colorResource(id = R.color.orange_500)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                ChoiceToImage(
                    choice = computerChoice,
                    modifier = Modifier.border(
                        width = 2.dp,
                        shape = CircleShape,
                        color = Color.DarkGray
                    )
                )
                Text("versus",  fontSize = 20.sp)
                ChoiceToImage(
                    choice = userChoice,
                    modifier = Modifier.border(
                        width = 2.dp,
                        shape = CircleShape,
                        color = Color.DarkGray
                    )
                )
            }

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.computer),
                        fontSize = 20.sp,
                    )
                    Text(
                        text = choiceToString(choice = computerChoice),
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.purple_500)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.you),
                        fontSize = 20.sp,
                    )
                    Text(
                        text = choiceToString(choice = userChoice),
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.purple_500)
                    )
                }
            }

            Button(onClick = onReplay) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.replay),
                    fontSize = 20.sp
                )
            }
        }
    }
}

