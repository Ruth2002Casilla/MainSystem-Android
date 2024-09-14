package edu.ucne.composedemo.mainsystemtickets.presentation.navigation

import kotlinx.serialization.Serializable


sealed class Screen {
    @Serializable
    object Home : Screen()

    // Prioridad
    @Serializable
    object ScreenPrioridades : Screen()
    @Serializable
    data class ListPrioridades(val prioridadId: Int) : Screen()

    // Sistema
    @Serializable
    object ScreenSistemas : Screen()
    @Serializable
    data class ListSistemas(val sistemaId: Int) : Screen()

    // Cliente
    @Serializable
    object ScreenClientes : Screen()
    @Serializable
    data class ListClientes(val clienteId: Int) : Screen()

    // Ticket
    @Serializable
    object ScreenTickets : Screen()
    @Serializable
    data class ListTickets(val ticketId: Int) : Screen()

}
