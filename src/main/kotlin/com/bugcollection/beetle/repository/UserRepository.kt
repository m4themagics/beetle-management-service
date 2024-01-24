package com.bugcollection.beetle.repository

import com.bugcollection.beetle.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int> {
    fun findUserById(id: Int): List<User>
}
