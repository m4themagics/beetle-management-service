package com.bugcollection.beetle.service

import com.bugcollection.beetle.model.Beetle

interface BeetleService {

    fun findAllBeetles(): List<Beetle>

    fun findBeetleById(beetleId: Int): Beetle?

    fun saveBeetle(beetle: Beetle): Beetle

    fun updateBeetle(beetleId: Int, beetleDetails: Beetle): Beetle?

    fun deleteBeetle(beetleId: Int): Boolean
}
