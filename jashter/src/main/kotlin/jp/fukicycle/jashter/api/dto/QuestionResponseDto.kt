package jp.fukicycle.jashter.api.dto

import jp.fukicycle.jashter.api.model.PartOfSpeech

data class QuestionResponseDto(
        val correctId: Long,
        val levelId: Long,
        val partOfSpeechId: Long,
        val choices: List<QuestionChoice>
)

data class QuestionChoice(
        val wordId: Long,
        val meaningOfWordId: Long,
        val meaning: String
)