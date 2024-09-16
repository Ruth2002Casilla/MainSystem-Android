package edu.ucne.composedemo.mainsystemtickets.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Clientes")
data class ClienteEntity (
    @PrimaryKey(autoGenerate = true)
    val clienteId: Int? = null,
    var nombre: String? = "",
    var telefono: String? = null,
    var celular: String? = null,
    var RNC: String? = null,
    var email: String? = "",
    var direccion: String? = ""
)