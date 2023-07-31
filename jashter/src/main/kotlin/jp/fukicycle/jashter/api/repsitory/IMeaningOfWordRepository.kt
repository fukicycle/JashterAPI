package jp.fukicycle.jashter.api.repsitory

import jp.fukicycle.jashter.api.model.MeaningOfWord
import org.springframework.data.repository.CrudRepository

interface IMeaningOfWordRepository : CrudRepository<MeaningOfWord,Long>{
}