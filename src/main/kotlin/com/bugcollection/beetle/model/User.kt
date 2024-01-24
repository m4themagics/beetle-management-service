package com.bugcollection.beetle.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "users", schema = "public", catalog = "BugCollection")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(nullable = false, unique = true)
    var username: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var beetles: Set<Beetle> = HashSet(),

    @OneToMany(mappedBy = "offer", cascade = [CascadeType.ALL], orphanRemoval = true)
    var exchangeOffers: Set<ExchangeOffers> = HashSet(),

    @ManyToMany
    @JoinTable(
        name = "users_beetles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "beetle_id")]
    )
    var collectedBeetles: Set<Beetle> = HashSet()
)
