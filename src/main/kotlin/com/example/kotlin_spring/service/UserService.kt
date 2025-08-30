package com.example.kotlin_spring.service

import com.example.kotlin_spring.application.UserDTO
import com.example.kotlin_spring.domain.User
import com.example.kotlin_spring.infrastructure.toDto
import com.example.kotlin_spring.infrastructure.UserRepository
import com.example.kotlin_spring.infrastructure.toDomain
import com.example.kotlin_spring.infrastructure.toEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getAllUsers(): List<UserDTO> {
        return userRepository.findAll().map {
            it.toDomain().toDto()
        }
    }


    fun createUser(user: UserDTO): UserDTO {
        val existsUser = userRepository.findById(user.id)
        if (existsUser.isPresent) {
            throw IllegalArgumentException("User with id ${user.id} already exists")
        }
        val user = User(
            id = user.id,
            name = user.name,
            email = user.email,
            createdAt = LocalDateTime.now(),
        )
        userRepository.save(user.toEntity())
        return user.toDto()
    }

    fun deleteUser(id: String) {
        val uuid = UUID.fromString(id)
        val user = userRepository.findById(uuid)
        if (user.isEmpty) {
            throw IllegalArgumentException("User with id $id does not exist")
        }
        userRepository.deleteById(uuid)
    }

    fun updateUser(updatedUser: UserDTO): UserDTO {
        val existResult = userRepository.findById(updatedUser.id)

        if (existResult.isEmpty) {
            throw IllegalArgumentException("User with id ${updatedUser.id} does not exist")
        }

        val user = existResult.get().toDomain().copy(
            name = updatedUser.name,
        )

        userRepository.save(user.toEntity())
        return user.toDto()
    }
}