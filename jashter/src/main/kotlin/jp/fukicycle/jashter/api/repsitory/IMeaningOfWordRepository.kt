package jp.fukicycle.jashter.api.repsitory

import jp.fukicycle.jashter.api.model.MeaningOfWord
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IMeaningOfWordRepository : CrudRepository<MeaningOfWord, Long> {
    fun findAllByLevelIdAndPartOfSpeechId(levelId: Long, partOfSpeechId: Long): List<MeaningOfWord>
    fun findAllByLevelId(levelId: Long): List<MeaningOfWord>
}