package jp.fukicycle.jashter.api.model

import jakarta.persistence.*

@Entity
@Table(name = "Words")
data class Word(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        val id: Long,
        @Column(name = "Word")
        val word: String
)
