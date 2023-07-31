package jp.fukicycle.jashter.api.service

import jp.fukicycle.jashter.api.dto.QuestionResponseDto
import jp.fukicycle.jashter.api.model.MeaningOfWord
import jp.fukicycle.jashter.api.repsitory.IMeaningOfWordRepository
import jp.fukicycle.jashter.api.repsitory.IWordRepository
import org.springframework.beans.factory.annotation.Autowired

class QuestionService : IQuestionService {

    @Autowired
    lateinit var wordRepository: IWordRepository

    @Autowired
    lateinit var meaningOfWordRepository: IMeaningOfWordRepository
    override fun create(levelId: Long, partOfSpeechId: Long): List<QuestionResponseDto> {
        TODO()
    }
}