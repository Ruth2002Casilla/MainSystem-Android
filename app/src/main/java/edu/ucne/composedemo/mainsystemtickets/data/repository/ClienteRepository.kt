package edu.ucne.composedemo.mainsystemtickets.data.repository

import edu.ucne.composedemo.mainsystemtickets.data.local.dao.ClienteDao
import edu.ucne.composedemo.mainsystemtickets.data.local.entity.ClienteEntity
import javax.inject.Inject

class ClienteRepository @Inject constructor(
    private val clienteDao: ClienteDao
) {
    suspend fun save(cliente: ClienteEntity) = clienteDao.save(cliente)

    suspend fun getCliente(id: Int) = clienteDao.find(id)

    suspend fun delete(cliente: ClienteEntity) = clienteDao.delete(cliente)

    fun getClientes() = clienteDao.getAll()
}
