package jp.fukicycle.jashter.api.service

import jp.fukicycle.jashter.api.dto.response.LevelResponseDto

interface ILevelService {
    fun get(): List<LevelResponseDto>
}