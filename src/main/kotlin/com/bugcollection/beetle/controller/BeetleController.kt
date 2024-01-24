package com.bugcollection.beetle.controller

import com.bugcollection.beetle.exception.ResourceNotFoundException
import com.bugcollection.beetle.model.Beetle
import com.bugcollection.beetle.model.User
import com.bugcollection.beetle.service.BeetleService
import com.bugcollection.beetle.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/beetles")
class BeetleController(
    private val beetleService: BeetleService,
    private val userService: UserService // Добавлен сервис для работы с пользователями
) {

    // Получение списка всех жуков
    @GetMapping
    fun getAllBeetles(): List<Beetle> = beetleService.findAllBeetles()

    // Получение жука по ID
    @GetMapping("/{id}")
    fun getBeetleById(@PathVariable(value = "id") beetleId: Int): ResponseEntity<Beetle> =
        beetleService.findBeetleById(beetleId)?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    // Создание нового жука
    @PostMapping("/{userId}")
    fun createBeetle(@PathVariable(value = "userId") userId: Int,
                     @RequestBody beetle: Beetle): ResponseEntity<Beetle> {
        val user: User = userService.findUserById(userId) ?: throw ResourceNotFoundException("User not found with id $userId")
        beetle.user = user
        val savedBeetle = beetleService.saveBeetle(beetle)
        return ResponseEntity.ok(savedBeetle)
    }


    // Обновление информации о жуке
    @PutMapping("/{id}")
    fun updateBeetle(@PathVariable(value = "id") beetleId: Int,
                     @RequestBody beetleDetails: Beetle): ResponseEntity<Beetle> {
        beetleService.findBeetleById(beetleId)
            ?: throw ResourceNotFoundException("Beetle not found with id $beetleId")
        val updatedBeetle = beetleService.updateBeetle(beetleId, beetleDetails)
        return ResponseEntity.ok(updatedBeetle)
    }

    // Удаление жука
    @DeleteMapping("/{id}")
    fun deleteBeetle(@PathVariable(value = "id") beetleId: Int): ResponseEntity<Void> =
        if (beetleService.deleteBeetle(beetleId)) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
}
