package com.example.kotlin_spring.application

import com.example.kotlin_spring.domain.UserEntity
import java.time.LocalDateTime

fun UserDTO.toEntity(
    createdAt: LocalDateTime,
): UserEntity {
    return UserEntity(
        id = this.id,
        name = this.name,
        email = this.email,
        createdAt = createdAt,
    )
}

fun UserEntity.toDto(): UserDTO {
    return UserDTO(
        id = this.id,
        name = this.name,
        email = this.email,
    )
}