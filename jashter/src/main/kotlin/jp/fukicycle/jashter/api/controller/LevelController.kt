package jp.fukicycle.jashter.api.controller

import jp.fukicycle.jashter.api.dto.response.LevelResponseDto
import jp.fukicycle.jashter.api.dto.response.PartOfSpeechResponseDto
import jp.fukicycle.jashter.api.service.ILevelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/level")
class LevelController {
    @Autowired
    lateinit var levelService: ILevelService

    @GetMapping("")
    fun get(authentication: Authentication): ResponseEntity<List<LevelResponseDto>> {
        return ResponseEntity.ok(levelService.get())
    }
}