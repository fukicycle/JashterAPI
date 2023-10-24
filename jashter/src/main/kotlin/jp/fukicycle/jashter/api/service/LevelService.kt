package jp.fukicycle.jashter.api.service

import jp.fukicycle.jashter.api.dto.response.LevelResponseDto
import jp.fukicycle.jashter.api.repsitory.ILevelRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LevelService : ILevelService {
    @Autowired
    lateinit var levelRepository: ILevelRepository
    override fun get(): List<LevelResponseDto> {
        val items = mutableListOf<LevelResponseDto>()
        val levels = levelRepository.findAll()
        for (level in levels) {
            items.add(LevelResponseDto(level.id, level.name))
        }
        return items
    }
}