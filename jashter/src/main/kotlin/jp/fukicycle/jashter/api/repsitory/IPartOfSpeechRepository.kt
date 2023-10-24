package jp.fukicycle.jashter.api.repsitory

import jp.fukicycle.jashter.api.model.PartOfSpeech
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IPartOfSpeechRepository : CrudRepository<PartOfSpeech, Long> {
}