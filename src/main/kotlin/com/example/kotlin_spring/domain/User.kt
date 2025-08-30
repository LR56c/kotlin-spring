package com.example.kotlin_spring.domain

import java.time.LocalDateTime
import java.util.UUID

data class User(
    val id: UUID,
    val name: String,
    val email: String,
    val createdAt: LocalDateTime
) {
}