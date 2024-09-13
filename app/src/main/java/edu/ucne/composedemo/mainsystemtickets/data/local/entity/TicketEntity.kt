package edu.ucne.composedemo.mainsystemtickets.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Tickets",
    foreignKeys = [
        ForeignKey(
            entity = PrioridadEntity::class,
            parentColumns = ["prioridadId"],
            childColumns = ["prioridadId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SistemaEntity::class,
            parentColumns = ["sistemaId"],
            childColumns = ["sistemaId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ClienteEntity::class,
            parentColumns = ["clienteId"],
            childColumns = ["clienteId"],
            onDelete = ForeignKey.CASCADE
        )

    ]
)
data class TicketEntity(
    @PrimaryKey(autoGenerate = true)
    val ticketId: Int? = null,
    val prioridadId: Int? = null,
    val clienteId: Int? = null,
    val sistemaId: Int? = null,
    val fecha: Long = System.currentTimeMillis(), // Convertir Fecha
    var solicitadoPor: String? = "",
    var asunto: String? = "",
    var descripcion: String? = "",
)
