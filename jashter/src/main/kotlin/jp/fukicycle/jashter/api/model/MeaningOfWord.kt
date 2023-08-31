package jp.fukicycle.jashter.api.model

import jakarta.persistence.*

@Entity
@Table(name = "MeaningOfWords")
data class MeaningOfWord(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        val id: Long,
        @Column(name = "PartOfSpeechID")
        val partOfSpeechId: Long,
        @Column(name = "WordID")
        val wordId: Long,
        @Column(name = "Meaning")
        val meaning: String,
        @Column(name = "LevelID")
        val levelId: Long
)
