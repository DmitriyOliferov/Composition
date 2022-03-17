package com.oliferov.composition.domain.repository

import com.oliferov.composition.domain.entity.GameSettings
import com.oliferov.composition.domain.entity.Level
import com.oliferov.composition.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}