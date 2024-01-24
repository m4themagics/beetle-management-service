package com.bugcollection.beetle.service.Impl

import com.bugcollection.beetle.exception.ResourceNotFoundException
import com.bugcollection.beetle.model.User
import com.bugcollection.beetle.repository.UserRepository
import com.bugcollection.beetle.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl @Autowired constructor(
    private val userRepository: UserRepository
) : UserService {

    override fun findUserById(id: Int): User? {
        return userRepository.findById(id).orElse(null)
    }

    override fun saveUser(user: User): User {
        return userRepository.save(user)
    }

    @Transactional
    override fun createUser(user: User): User {
        // Здесь могут быть дополнительные проверки и логика
        return userRepository.save(user)
    }

    @Transactional(readOnly = true)
    override fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    @Transactional(readOnly = true)
    override fun getUserById(id: Int): User {
        // Здесь можно добавить обработку исключения, если пользователь не найден
        return userRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("User not found with id: $id") }
    }

    @Transactional
    override fun updateUser(id: Int, userDetails: User): User {
        val user = userRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("User not found with id: $id") }

        user.username = userDetails.username
        user.password = userDetails.password
        user.email = userDetails.email
        // Обновление других полей...

        return userRepository.save(user)
    }

    @Transactional
    override fun deleteUser(id: Int) {
        val user = userRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("User not found with id: $id") }
        userRepository.delete(user)
    }
}
