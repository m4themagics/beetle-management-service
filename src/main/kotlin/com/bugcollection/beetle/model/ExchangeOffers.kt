package com.bugcollection.beetle.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "exchange_offers", schema = "public", catalog = "BugCollection")
data class ExchangeOffers(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id", nullable = false)
    val offer: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beetle_id", nullable = false)
    val beetle: Beetle,

    @Column(name = "requested_species", nullable = false)
    var requestedSpecies: String,

    @Column(nullable = false)
    var status: String,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)
