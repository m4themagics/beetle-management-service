package com.bugcollection.beetle.repository

import com.bugcollection.beetle.model.Beetle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BeetleRepository : JpaRepository<Beetle, Int> {
    fun findBySpecies(species: String): List<Beetle>

    fun findByNameContaining(name: String): List<Beetle>
}
