package com.example.kotlin_spring.service

import com.example.kotlin_spring.application.UserDTO
import com.example.kotlin_spring.application.toDto
import com.example.kotlin_spring.domain.UserEntity
import com.example.kotlin_spring.domain.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getAllUsers() : List<UserDTO> {
        return userRepository.findAll().map {
            it.toDto()
        }
    }

    fun getUserById(id: String) : UserDTO {
        val userEntity = userRepository.findById(id)
        if (userEntity.isEmpty) {
            throw Exception("User not found")
        }
        return userEntity.get().toDto()
    }

    fun createUser(user: UserDTO) : UserDTO {
        val userEntity = UserEntity(
            id = user.id,
            name = user.name,
            email = user.email,
            createdAt = LocalDateTime.now(),
        )
        userRepository.save(userEntity)
        return userEntity.toDto()
    }

    fun deleteUser(id: String) = userRepository.deleteById(id)

    fun updateUser(id: String, updatedUser: UserDTO): UserEntity? {
        val existResult = userRepository.findById(id)

        if (existResult.isEmpty) {
            return null
        }

        val user = existResult.get()

        val userToUpdate = UserEntity(
            id = user.id,
            name = updatedUser.name,
            email = updatedUser.email,
            createdAt = user.createdAt,
        )
        return userRepository.save(userToUpdate)
    }
}