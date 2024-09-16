package edu.ucne.composedemo.mainsystemtickets.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Tickets")
data class TicketEntity (
    @PrimaryKey
    val TicketId: Int? = null,
    var PrioridadId: Int? = null,
    var SistemaId: Int? = null,
    var Fecha: String? = "",
    var ClienteId: Int? = null,
    var Asunto: String = "",
    var Descripcion: String = ""
)