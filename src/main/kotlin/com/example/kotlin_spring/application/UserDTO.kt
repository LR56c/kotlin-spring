package com.example.kotlin_spring.application

import java.util.UUID


data class UserDTO(
    val id: UUID,
    val name: String,
    val email: String,
    ) {
}