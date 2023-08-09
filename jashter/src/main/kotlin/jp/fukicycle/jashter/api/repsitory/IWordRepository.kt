package jp.fukicycle.jashter.api.repsitory

import jp.fukicycle.jashter.api.model.Word
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IWordRepository : CrudRepository<Word,Long> {
}