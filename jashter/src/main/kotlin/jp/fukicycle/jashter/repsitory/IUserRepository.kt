package jp.fukicycle.jashter.repsitory

import jp.fukicycle.jashter.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IUserRepository : CrudRepository<User, Int> {
    fun findByUsernameAndPassword(username: String, password: String): User?
}