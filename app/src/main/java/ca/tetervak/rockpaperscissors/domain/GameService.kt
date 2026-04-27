package ca.tetervak.rockpaperscissors.domain

import kotlin.random.Random

class GameService(
    private val random: Random = Random.Default
){

    private val choices: List<Choice> =
        Choice.entries.slice(0..2)

    fun getRandomChoice(): Choice =
        choices.random(random)

    fun getGameResult(
        userChoice: Choice,
        computerChoice: Choice
    ): GameResult =
        when (userChoice) {
            Choice.PAPER -> when (computerChoice) {
                Choice.PAPER -> GameResult.REPLAY
                Choice.ROCK -> GameResult.USER_WINS
                Choice.SCISSORS -> GameResult.COMPUTER_WINS
                else -> GameResult.UNKNOWN
            }
            Choice.ROCK -> when (computerChoice) {
                Choice.PAPER -> GameResult.COMPUTER_WINS
                Choice.ROCK -> GameResult.REPLAY
                Choice.SCISSORS -> GameResult.USER_WINS
                else -> GameResult.UNKNOWN
            }
            Choice.SCISSORS -> when (computerChoice) {
                Choice.PAPER -> GameResult.USER_WINS
                Choice.ROCK -> GameResult.COMPUTER_WINS
                Choice.SCISSORS -> GameResult.REPLAY
                else -> GameResult.UNKNOWN
            }
            else -> GameResult.UNKNOWN
        }
}