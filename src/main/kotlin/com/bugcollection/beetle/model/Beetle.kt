package com.bugcollection.beetle.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "beetles", schema = "public", catalog = "BugCollection")
data class Beetle(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "species", nullable = false)
    var species: String,

    @Column(name = "description", columnDefinition = "TEXT")
    var description: String? = null,

    @Column(name = "image_url")
    var imageUrl: String? = null,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToMany(mappedBy = "collectedBeetles")
    val collectors: Set<User> = HashSet()
)
