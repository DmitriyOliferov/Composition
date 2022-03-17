package com.oliferov.composition.domain.entity

data class GameResult(
    val winner: Boolean,
    val countOfRightAnswers: Int,
    val countsOfQuestions: Int,
    val gameSettings: GameSettings
)