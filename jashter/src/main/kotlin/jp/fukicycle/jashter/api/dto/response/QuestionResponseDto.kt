package jp.fukicycle.jashter.api.dto.response

data class QuestionResponseDto(
        val correctWordId: Long,
        val correctMeaningOfWordId: Long,
        val levelId: Long,
        val partOfSpeechId: Long,
        val choices: List<QuestionChoice>
)

data class QuestionChoice(
        val wordId: Long,
        val meaningOfWordId: Long,
        val meaning: String
)