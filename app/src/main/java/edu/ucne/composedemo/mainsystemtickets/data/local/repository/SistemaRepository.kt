package edu.ucne.composedemo.mainsystemtickets.data.local.repository

import edu.ucne.composedemo.mainsystemtickets.data.local.dao.SistemaDao
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.SistemaEntity
import javax.inject.Inject

class SistemaRepository @Inject constructor(
    private val sistemaDao: SistemaDao
) {
    suspend fun save(sistema: SistemaEntity) = sistemaDao.save(sistema)

    suspend fun getSistema(id: Int) = sistemaDao.find(id)

    suspend fun delete(sistema: SistemaEntity) = sistemaDao.delete(sistema)

    fun getSistema() = sistemaDao.getAll()
}