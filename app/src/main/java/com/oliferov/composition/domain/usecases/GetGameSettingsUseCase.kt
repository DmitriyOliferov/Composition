package com.oliferov.composition.domain.usecases

import com.oliferov.composition.domain.entity.GameSettings
import com.oliferov.composition.domain.entity.Level
import com.oliferov.composition.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(level: Level): GameSettings{
        return repository.getGameSettings(level)
    }
}