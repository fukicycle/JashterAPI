package jp.fukicycle.jashter.api.service

import jp.fukicycle.jashter.api.dto.response.PartOfSpeechResponseDto

interface IPartOfSpeechService {
    fun get(): List<PartOfSpeechResponseDto>
}