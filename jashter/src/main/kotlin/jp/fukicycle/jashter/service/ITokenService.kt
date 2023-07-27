package jp.fukicycle.jashter.service

import jp.fukicycle.jashter.model.User

interface ITokenService {
    fun generate(user: User): String
    fun parse(token: String): User?
}