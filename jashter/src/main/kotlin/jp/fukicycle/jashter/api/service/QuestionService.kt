package jp.fukicycle.jashter.api.service

import jp.fukicycle.jashter.api.dto.response.QuestionChoice
import jp.fukicycle.jashter.api.dto.response.QuestionResponseDto
import jp.fukicycle.jashter.api.model.MeaningOfWord
import jp.fukicycle.jashter.api.repsitory.IMeaningOfWordRepository
import jp.fukicycle.jashter.api.repsitory.IWordRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QuestionService : IQuestionService {

    @Autowired
    lateinit var meaningOfWordRepository: IMeaningOfWordRepository
    override fun create(levelId: Long, partOfSpeechId: Long): List<QuestionResponseDto> {
        val items = mutableListOf<QuestionResponseDto>()
        val meaningOfWords = meaningOfWordRepository.findAllByLevelIdAndPartOfSpeechId(levelId, partOfSpeechId)
        for (meaningOfWord in meaningOfWords.withIndex()) {
            if (meaningOfWord.index >= 100) break
            val choice = mutableListOf<QuestionChoice>()
            for (choiceItem in getRandomChoice(meaningOfWord.value.wordId, meaningOfWord.value.partOfSpeechId)) {
                choice.add(QuestionChoice(choiceItem.wordId, choiceItem.id, choiceItem.meaning))
            }
            choice.add(QuestionChoice(meaningOfWord.value.wordId, meaningOfWord.value.id, meaningOfWord.value.meaning))
            choice.shuffle()
            items.add(
                QuestionResponseDto(
                    meaningOfWord.value.wordId,
                    meaningOfWord.value.id,
                    levelId,
                    partOfSpeechId,
                    choice
                )
            )
        }
        return items
    }

    private fun getRandomChoice(baseWordId: Long, partOfSpeechId: Long): List<MeaningOfWord> {
        val items = mutableListOf<MeaningOfWord>()
        val meaningOfWords = meaningOfWordRepository.findAll()
            .filter { a -> a.wordId != baseWordId && a.partOfSpeechId == partOfSpeechId }
        var count = 0
        while (true) {
            val random = meaningOfWords.random()
            if (items.equals(random)) {
                continue
            } else {
                if (count >= 3) break
                items.add(random)
                count++
            }
        }
        return items
    }
}