package jp.fukicycle.jashter.api.model

import jakarta.persistence.*

@Entity
@Table(name = "Levels")
data class Level(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        val id: Long,
        @Column(name = "Name")
        val name: String
)
