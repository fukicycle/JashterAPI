package jp.fukicycle.jashter.api.model

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "MeaningOfWordLearningHistories")
data class MeaningOfWordLearningHistory(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        val id: Long,
        @Column(name = "UserID")
        val userId: Long,
        @Column(name = "QuestionMeaningOfWordID")
        val questionMeaningOfWordId: Long,
        @Column(name = "AnswerMeaningOfWordID")
        val answerMeaningOfWordId: Long,
        @Column(name = "Date")
        val date: LocalDateTime,
        @Column(name = "IsDone")
        val isDone: Boolean
)
