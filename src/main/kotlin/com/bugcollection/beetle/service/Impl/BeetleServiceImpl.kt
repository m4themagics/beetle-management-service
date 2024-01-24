package com.bugcollection.beetle.service.Impl

import com.bugcollection.beetle.model.Beetle
import com.bugcollection.beetle.repository.BeetleRepository
import com.bugcollection.beetle.service.BeetleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BeetleServiceImpl @Autowired constructor(
    private val beetleRepository: BeetleRepository
) : BeetleService {

    override fun findAllBeetles(): List<Beetle> {
        return beetleRepository.findAll()
    }

    override fun findBeetleById(beetleId: Int): Beetle? {
        return beetleRepository.findById(beetleId).orElse(null)
    }

    override fun saveBeetle(beetle: Beetle): Beetle {
        return beetleRepository.save(beetle)
    }

    override fun updateBeetle(beetleId: Int, beetleDetails: Beetle): Beetle? {
        val existingBeetle = beetleRepository.findById(beetleId).orElse(null) ?: return null
        return existingBeetle.apply {
            name = beetleDetails.name
            species = beetleDetails.species
            description = beetleDetails.description
            imageUrl = beetleDetails.imageUrl
        }.let {
            beetleRepository.save(it)
        }
    }

    override fun deleteBeetle(beetleId: Int): Boolean {
        return beetleRepository.findById(beetleId).map { beetle ->
            beetleRepository.delete(beetle)
            true
        }.orElse(false)
    }
}
