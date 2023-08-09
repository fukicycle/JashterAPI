package jp.fukicycle.jashter.api.model

import jakarta.persistence.*

@Entity
@Table(name = "Users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        val id: Long,
        @Column(name = "Username")
        val username: String,
        @Column(name = "Password")
        val password: String,
        @Column(name = "FirstName")
        val firstName: String,
        @Column(name = "LastName")
        val lastName: String,
        @Column(name = "Icon")
        val icon: String,
        @Column(name = "Token")
        val token: String,
        @Column(name = "NickName")
        val nickname: String
)
