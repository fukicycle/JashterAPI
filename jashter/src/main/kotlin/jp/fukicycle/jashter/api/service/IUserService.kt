package jp.fukicycle.jashter.api.service

import jp.fukicycle.jashter.api.model.User

interface IUserService {
    fun findById(id: Long): User
    fun findAll():List<User>
}