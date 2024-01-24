package com.bugcollection.beetle.controller

import com.bugcollection.beetle.model.User
import com.bugcollection.beetle.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    // Create a new user (C)
    @PostMapping
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        val newUser = userService.createUser(user)
        return ResponseEntity.ok(newUser)
    }

    // Read all users (R)
    @GetMapping
    fun getAllUsers(): ResponseEntity<List<User>> {
        val users = userService.getAllUsers()
        return ResponseEntity.ok(users)
    }

    // Read a single user by ID (R)
    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Int): ResponseEntity<User> {
        val user = userService.getUserById(id)
        return ResponseEntity.ok(user)
    }

    // Update a user (U)
    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Int, @RequestBody userDetails: User): ResponseEntity<User> {
        val updatedUser = userService.updateUser(id, userDetails)
        return ResponseEntity.ok(updatedUser)
    }

    // Delete a user (D)
    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Int): ResponseEntity<Void> {
        userService.deleteUser(id)
        return ResponseEntity.ok().build()
    }
}
