package com.example.kotlin_spring.controller

import com.example.kotlin_spring.application.UserDTO
import com.example.kotlin_spring.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

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
    ): UserDTO {
        return userService.updateUser(dto)
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable("id") id: String
    ) {
        userService.deleteUser(id)
    }
}