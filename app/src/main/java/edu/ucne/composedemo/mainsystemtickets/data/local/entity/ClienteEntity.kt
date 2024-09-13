package edu.ucne.composedemo.mainsystemtickets.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Clientes")
data class ClienteEntity (
    @PrimaryKey(autoGenerate = true)
    val clienteId: Int? = null,
    var nombre: String? = "",
    var telefono: Int? = null,
    var celular: Int? = null,
    var RNC: Int? = null,
    var email: String? = "",
    var direction: String? = ""
)