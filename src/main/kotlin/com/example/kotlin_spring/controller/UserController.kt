package com.example.kotlin_spring.controller

import com.example.kotlin_spring.application.UserDTO
import com.example.kotlin_spring.application.toDto
import com.example.kotlin_spring.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
     private val userService: UserService
) {

    @GetMapping
    fun get(
    ): List<UserDTO> {
        return userService.getAllUsers()
    }

    @PostMapping
    fun post(
        @RequestBody dto: UserDTO
    ): UserDTO {
        return userService.createUser(dto)
    }

    @PutMapping
    fun put(
        @RequestBody dto: UserDTO
    ): ResponseEntity<UserDTO> {
        val updatedUser = userService.updateUser(dto.id, dto)
        return if (updatedUser == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(updatedUser.toDto())
        }
    }

    @DeleteMapping
    fun delete(
        @RequestBody dto: UserDTO
    ) {
        userService.deleteUser(dto.id)
    }
}