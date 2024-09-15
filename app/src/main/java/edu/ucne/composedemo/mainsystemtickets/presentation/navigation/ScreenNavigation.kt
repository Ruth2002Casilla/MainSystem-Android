package edu.ucne.composedemo.mainsystemtickets.presentation.navigation

import kotlinx.serialization.Serializable

sealed class ScreenNavigation {
    @Serializable
    object Home : ScreenNavigation()

    //Prioridades
    @Serializable
    object ControlPanelPrioridades : ScreenNavigation()
    @Serializable
    object CrearPrioridades : ScreenNavigation()
    @Serializable
    data class EditarPrioridades(val prioridadId: Int?) : ScreenNavigation()
    @Serializable
    data class EliminarPrioridades(val prioridadId: Int) : ScreenNavigation()


    //Tickets
    @Serializable
    object ControlPanelTickets : ScreenNavigation()
    @Serializable
    object CrearTickets : ScreenNavigation()
    @Serializable
    data class EditarTickets(val ticketId: Int?) : ScreenNavigation()
    @Serializable
    data class EliminarTickets(val ticketId: Int) : ScreenNavigation()

    @Serializable
    object Informacion : ScreenNavigation()
}
