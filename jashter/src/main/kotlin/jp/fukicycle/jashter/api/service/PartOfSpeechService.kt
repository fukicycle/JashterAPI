package jp.fukicycle.jashter.api.service

import jp.fukicycle.jashter.api.dto.response.PartOfSpeechResponseDto
import jp.fukicycle.jashter.api.repsitory.IPartOfSpeechRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PartOfSpeechService : IPartOfSpeechService {
    @Autowired
    lateinit var partOfSpeechRepository: IPartOfSpeechRepository
    override fun get(): List<PartOfSpeechResponseDto> {
        val items = mutableListOf<PartOfSpeechResponseDto>()
        val partOfSpeeches = partOfSpeechRepository.findAll()
        for (partOfSpeech in partOfSpeeches) {
            items.add(PartOfSpeechResponseDto(partOfSpeech.id, partOfSpeech.name, partOfSpeech.inJapanese))
        }
        return items
    }
}