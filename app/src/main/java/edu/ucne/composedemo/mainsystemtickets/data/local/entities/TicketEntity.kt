package edu.ucne.composedemo.mainsystemtickets.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Tickets")
data class TicketEntity (
    @PrimaryKey
    val ticketId: Int,
    val prioridadId: Int? = null,
    val sistemaId: Int? = null,
    val fecha: String? = "",
    val clienteId: Int? = null,
    val asunto: String = "",
    val descripcion: String = ""
)