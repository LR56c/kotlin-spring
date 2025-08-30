package com.example.kotlin_spring.infrastructure

import com.example.kotlin_spring.application.UserDTO
import com.example.kotlin_spring.domain.User

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        name = this.name,
        email = this.email,
        createdAt = this.createdAt,
    )
}

fun User.toDto(): UserDTO {
    return UserDTO(
        id = this.id,
        name = this.name,
        email = this.email,
    )
}

fun UserEntity.toDomain(): User {
    return User(
        id = this.id,
        name = this.name,
        email = this.email,
        createdAt = this.createdAt,
    )
}