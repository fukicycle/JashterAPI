package jp.fukicycle.jashter.api.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
data class User(
        @Id
        val id:Long,
        @Column(name = "Username")
        val username:String,
        @Column(name = "Password")
        val password:String,
        @Column(name = "FirstName")
        val firstName:String,
        @Column(name = "LastName")
        val lastName:String,
        @Column(name = "Icon")
        val icon:String,
        @Column(name = "Token")
        val token:String,
        @Column(name = "NickName")
        val nickname:String
)
