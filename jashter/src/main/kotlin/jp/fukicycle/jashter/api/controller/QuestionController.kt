package jp.fukicycle.jashter.api.controller

import jp.fukicycle.jashter.api.dto.request.QuestionRequestDto
import jp.fukicycle.jashter.api.dto.response.QuestionResponseDto
import jp.fukicycle.jashter.api.service.IQuestionService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/question")
class QuestionController(
        private val questionService: IQuestionService
) {
    @GetMapping("/get")
    fun get(authentication: Authentication, @RequestBody questionRequestDto: QuestionRequestDto): ResponseEntity<List<QuestionResponseDto>> {
        return ResponseEntity.ok(questionService.create(questionRequestDto.levelId, questionRequestDto.partOfSpeechId))
    }
}