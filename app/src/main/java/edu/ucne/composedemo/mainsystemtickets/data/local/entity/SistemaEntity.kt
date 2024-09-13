package edu.ucne.composedemo.mainsystemtickets.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Sistemas")
data class SistemaEntity (
    @PrimaryKey(autoGenerate = true)
    val sistemaId: Int? = null,
    var nombre: String? = ""
)

