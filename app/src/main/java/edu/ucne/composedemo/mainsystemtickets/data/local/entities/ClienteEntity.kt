package edu.ucne.composedemo.mainsystemtickets.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Clientes")
data class ClienteEntity (
    @PrimaryKey(autoGenerate = true)
    val clienteId: Int,
    val nombre: String? = "",
    val telefono: String? = null,
    val celular: String? = null,
    val RNC: String? = null,
    val email: String? = "",
    val direccion: String? = ""
)