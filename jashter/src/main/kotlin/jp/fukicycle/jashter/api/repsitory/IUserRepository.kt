package jp.fukicycle.jashter.api.repsitory

import jp.fukicycle.jashter.api.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IUserRepository : CrudRepository<User, Long> {
    fun findByUsernameAndPassword(username: String, password: String): User?
}