package edu.ucne.composedemo.mainsystemtickets.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    object Home : Screen()

    //Prioridades
    @Serializable
    object ControlPanelPrioridades : Screen()
    @Serializable
    object CrearPrioridades : Screen()
    @Serializable
    data class EditarPrioridades(val prioridadId: Int?) : Screen()
    @Serializable
    data class EliminarPrioridades(val prioridadId: Int) : Screen()

    //Sistemas
    @Serializable
    object ControlPanelSistemas : Screen()
    @Serializable
    object CrearSistemas : Screen()
    @Serializable
    data class EditarSistemas(val sistemaId: Int?) : Screen()
    @Serializable
    data class EliminarSistemas(val sistemaId: Int) : Screen()

    //Clientes
    @Serializable
    object ControlPanelClientes : Screen()
    @Serializable
    object CrearClientes : Screen()
    @Serializable
    data class EditarClientes(val clienteId: Int?) : Screen()
    @Serializable
    data class EliminarClientes(val clienteId: Int) : Screen()

    //Tickets
    @Serializable
    object ControlPanelTickets : Screen()
    @Serializable
    object CrearTickets : Screen()
    @Serializable
    data class EditarTickets(val ticketId: Int?) : Screen()
    @Serializable
    data class EliminarTickets(val ticketId: Int) : Screen()

    @Serializable
    object Informacion : Screen()
}
