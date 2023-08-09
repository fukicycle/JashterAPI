package jp.fukicycle.jashter.api.dto

import jp.fukicycle.jashter.api.model.PartOfSpeech

data class QuestionDto(
        val levelId: Long,
        val partOfSpeechId: Long
)
