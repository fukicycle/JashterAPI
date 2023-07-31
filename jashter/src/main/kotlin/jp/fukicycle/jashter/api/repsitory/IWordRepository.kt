package jp.fukicycle.jashter.api.repsitory

import jp.fukicycle.jashter.api.model.Word
import org.springframework.data.repository.CrudRepository

interface IWordRepository : CrudRepository<Word,Long> {
    fun findAllByLevelIDAndPartOfSpeechID(levelId: Long, partOfSpeechId: Long): List<Word>
}