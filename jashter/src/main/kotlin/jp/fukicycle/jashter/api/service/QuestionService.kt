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
    lateinit var wordRepository: IWordRepository

    @Autowired
    lateinit var meaningOfWordRepository: IMeaningOfWordRepository
    override fun create(levelId: Long, partOfSpeechId: Long): List<QuestionResponseDto> {
        val items = mutableListOf<QuestionResponseDto>()
        val meaningOfWords = meaningOfWordRepository.findAllByLevelIdAndPartOfSpeechId(levelId, partOfSpeechId)
        for (meaningOfWord in meaningOfWords) {
            val choice = mutableListOf<QuestionChoice>()
            getRandomChoice(meaningOfWord.wordId, meaningOfWord.partOfSpeechId).forEach {
                choice.add(QuestionChoice(it.wordId, it.id, it.meaning))
            }
            choice.add(QuestionChoice(meaningOfWord.wordId, meaningOfWord.id, meaningOfWord.meaning))
            choice.shuffle()
            items.add(QuestionResponseDto(meaningOfWord.wordId, meaningOfWord.id, levelId, partOfSpeechId, choice))
        }
        return items
    }

    private fun getRandomChoice(baseWordId: Long, partOfSpeechId: Long): List<MeaningOfWord> {
        val items = mutableListOf<MeaningOfWord>()
        val meaningOfWords = meaningOfWordRepository.findAll().filter { a -> a.wordId != baseWordId && a.partOfSpeechId == partOfSpeechId }
        var count = 0;
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