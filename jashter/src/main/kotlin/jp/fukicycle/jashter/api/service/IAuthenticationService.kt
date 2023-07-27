package jp.fukicycle.jashter.api.service

import jp.fukicycle.jashter.api.model.User

interface IAuthenticationService {
    fun login(username: String, password: String): String?
}