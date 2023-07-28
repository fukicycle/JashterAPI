package jp.fukicycle.jashter.api.model

import com.fasterxml.jackson.module.kotlin.jsonMapper
import jakarta.persistence.*

@Entity
@Table(name = "PartOfSpeech")
data class PartOfSpeech(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        val id: Long,
        @Column(name = "Name")
        val name: String,
        @Column(name = "InJapanese")
        val inJapanese: String
)
