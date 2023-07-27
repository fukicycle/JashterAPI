package jp.fukicycle.jashter.service

import jp.fukicycle.jashter.model.User

interface IAuthenticationService {
    fun login(username: String, password: String): String?
}