package com.bugcollection.beetle.service

import com.bugcollection.beetle.model.User

interface UserService {
    fun findUserById(id: Int): User?
    fun saveUser(user: User): User
    fun createUser(user: User): User
    fun getAllUsers(): List<User>
    fun getUserById(id: Int): User
    fun updateUser(id: Int, userDetails: User): User
    fun deleteUser(id: Int)
}