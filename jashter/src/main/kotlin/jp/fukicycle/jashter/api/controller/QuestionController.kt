package jp.fukicycle.jashter.api.controller

import jp.fukicycle.jashter.api.dto.QuestionDto
import jp.fukicycle.jashter.api.dto.QuestionResponseDto
import jp.fukicycle.jashter.api.model.User
import jp.fukicycle.jashter.api.service.IQuestionService
import jp.fukicycle.jashter.api.service.QuestionService
import org.springframework.http.HttpStatus
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
    fun get(authentication: Authentication, @RequestBody questionDto: QuestionDto): ResponseEntity<List<QuestionResponseDto>> {
        return ResponseEntity.ok(questionService.create(questionDto.levelId, questionDto.partOfSpeechId))
    }
}