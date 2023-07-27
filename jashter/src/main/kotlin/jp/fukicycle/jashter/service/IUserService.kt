package jp.fukicycle.jashter.service

import jp.fukicycle.jashter.model.User

interface IUserService {
    fun findById(id: Int): User
}