package com.example.kotlin_spring.infrastructure

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "users")

class UserEntity(
    @Id
    var id: UUID,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false, unique = true)
    var email: String,
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    var createdAt: LocalDateTime
) {
}