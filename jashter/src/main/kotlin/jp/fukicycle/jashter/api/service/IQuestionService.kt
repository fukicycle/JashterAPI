package jp.fukicycle.jashter.api.service

import jp.fukicycle.jashter.api.dto.QuestionResponseDto

interface IQuestionService {
    fun create(levelId: Long, partOfSpeechId: Long): List<QuestionResponseDto>
}