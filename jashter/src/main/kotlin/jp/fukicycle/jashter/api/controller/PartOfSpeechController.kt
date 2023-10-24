package jp.fukicycle.jashter.api.controller

import jp.fukicycle.jashter.api.dto.request.QuestionRequestDto
import jp.fukicycle.jashter.api.dto.response.PartOfSpeechResponseDto
import jp.fukicycle.jashter.api.dto.response.QuestionResponseDto
import jp.fukicycle.jashter.api.service.IPartOfSpeechService
import jp.fukicycle.jashter.api.service.IQuestionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/part-of-speech")
class PartOfSpeechController {
    @Autowired
    lateinit var partOfSpeechService: IPartOfSpeechService

    @GetMapping("")
    fun get(authentication: Authentication): ResponseEntity<List<PartOfSpeechResponseDto>> {
        return ResponseEntity.ok(partOfSpeechService.get())
    }
}