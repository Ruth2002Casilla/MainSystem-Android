package edu.ucne.composedemo.mainsystemtickets.data.repository

import edu.ucne.composedemo.mainsystemtickets.data.local.dao.PrioridadDao
import edu.ucne.composedemo.mainsystemtickets.data.local.entity.PrioridadEntity
import javax.inject.Inject

class PrioridadRepository @Inject constructor(
    private val prioridadDao: PrioridadDao
) {
    suspend fun save(prioridad: PrioridadEntity) = prioridadDao.save(prioridad)

    suspend fun getPrioridad(id: Int) = prioridadDao.find(id)

    suspend fun delete(prioridad: PrioridadEntity) = prioridadDao.delete(prioridad)

    fun getPrioridades() = prioridadDao.getAll()

    suspend fun getAllDias() = prioridadDao.getAllDias()
}
