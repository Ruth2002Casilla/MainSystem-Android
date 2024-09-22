package edu.ucne.composedemo.mainsystemtickets.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.composedemo.mainsystemtickets.data.local.dao.ClienteDao
import edu.ucne.composedemo.mainsystemtickets.data.local.dao.PrioridadDao
import edu.ucne.composedemo.mainsystemtickets.data.local.dao.SistemaDao
import edu.ucne.composedemo.mainsystemtickets.data.local.dao.TicketDao
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.ClienteEntity
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.PrioridadEntity
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.SistemaEntity
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.TicketEntity
import edu.ucne.composedemo.mainsystemtickets.presentation.component.NavigationDrawer
import edu.ucne.composedemo.mainsystemtickets.presentation.HomeScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.clientes.CreateClientesScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.clientes.DeleteClientesScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.clientes.EditClientesScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.clientes.IndexClientesScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.prioridades.CreatePrioridadesScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.prioridades.DeletePrioridadesScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.prioridades.EditPrioridadesScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.prioridades.IndexPrioridadesScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.sistemas.CreateSistemasScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.sistemas.DeleteSistemasScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.sistemas.EditSistemasScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.sistemas.IndexSistemasScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.tickets.CreateTicketsScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.tickets.DeleteTicketsScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.tickets.EditTicketScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.tickets.IndexTicketsScreen
import kotlinx.coroutines.flow.Flow


@Composable
fun NavigationNavHost(
    navHostController: NavHostController,
    PrioridadesLista: Flow<List<PrioridadEntity>>,
    prioridadDao: PrioridadDao,
    SistemasLista: Flow<List<SistemaEntity>>,
    sistemaDao: SistemaDao,
    ClientesLista: Flow<List<ClienteEntity>>,
    clienteDao: ClienteDao,
    TicketsLista: Flow<List<TicketEntity>>,
    ticketDao: TicketDao
) {
    val isDrawerVisible = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navHostController, startDestination = ScreenNavigation.Home::class.java.simpleName) {

            composable(ScreenNavigation.Home::class.java.simpleName) {
                HomeScreen(
                    onDrawerToggle = { isDrawerVisible.value = !isDrawerVisible.value }
                )
            }
                //Prioridades
            composable(ScreenNavigation.ControlPanelPrioridades::class.java.simpleName) {
                IndexPrioridadesScreen(
                    onDrawerToggle = {
                        isDrawerVisible.value = !isDrawerVisible.value
                    },
                    goToPrioridad = {
                        navHostController.navigate(ScreenNavigation.ControlPanelPrioridades::class.java.simpleName)
                    },
                    createPrioridad = {
                        navHostController.navigate(ScreenNavigation.CrearPrioridades::class.java.simpleName)
                    },
                    editPrioridad = {
                        navHostController.navigate(ScreenNavigation.EditarPrioridades::class.java.simpleName + "/${it}")
                    },
                    deletePrioridad = {
                        navHostController.navigate(ScreenNavigation.EliminarPrioridades::class.java.simpleName + "/${it}")
                    }
                )
            }

            composable(ScreenNavigation.CrearPrioridades::class.java.simpleName) {
                CreatePrioridadesScreen(
                    onDrawerToggle = {
                        isDrawerVisible.value = !isDrawerVisible.value
                    },
                    goToPrioridad = {
                        navHostController.navigate(ScreenNavigation.ControlPanelPrioridades::class.java.simpleName)
                    }
                )
            }

            composable(ScreenNavigation.EditarPrioridades::class.java.simpleName + "/{prioridadId}") { backStackEntry ->
                val prioridadId = backStackEntry.arguments?.getString("prioridadId")?.toIntOrNull()
                if (prioridadId != null) {
                    EditPrioridadesScreen(
                        prioridadId = prioridadId,
                        onDrawerToggle = {
                            isDrawerVisible.value = !isDrawerVisible.value
                        },
                        goToPrioridad = {
                            navHostController.navigate(ScreenNavigation.ControlPanelPrioridades::class.java.simpleName)
                        }
                    )
                }
            }

            composable(ScreenNavigation.EliminarPrioridades::class.java.simpleName + "/{prioridadId}") { backStackEntry ->
                val prioridadId = backStackEntry.arguments?.getString("prioridadId")?.toIntOrNull()
                if (prioridadId != null) {
                    DeletePrioridadesScreen(
                        prioridadId = prioridadId,
                        onDrawerToggle = {
                            isDrawerVisible.value = !isDrawerVisible.value
                        },
                        goToPrioridad = {
                            navHostController.navigate(ScreenNavigation.ControlPanelPrioridades::class.java.simpleName) {
                                popUpTo(ScreenNavigation.ControlPanelPrioridades::class.java.simpleName) { inclusive = true }
                            }
                        }
                    )
                }
            }


            //Clientes
            composable(ScreenNavigation.ControlPanelClientes::class.java.simpleName) {
                IndexClientesScreen(
                    onDrawerToggle = {
                        isDrawerVisible.value = !isDrawerVisible.value
                    },
                    goToCliente = {
                        navHostController.navigate(ScreenNavigation.ControlPanelClientes::class.java.simpleName)
                    },
                    createCliente = {
                        navHostController.navigate(ScreenNavigation.CrearClientes::class.java.simpleName)
                    },
                    editCliente = {
                        navHostController.navigate(ScreenNavigation.EditarClientes::class.java.simpleName + "/${it}")
                    },
                    deleteCliente = {
                        navHostController.navigate(ScreenNavigation.EliminarClientes::class.java.simpleName + "/${it}")
                    }
                )
            }

            composable(ScreenNavigation.CrearClientes::class.java.simpleName) {
                CreateClientesScreen(
                    onDrawerToggle = {
                        isDrawerVisible.value = !isDrawerVisible.value
                    },
                    goToCliente = {
                        navHostController.navigate(ScreenNavigation.ControlPanelClientes::class.java.simpleName)
                    }
                )
            }

            composable(ScreenNavigation.EditarClientes::class.java.simpleName + "/{clienteId}") { backStackEntry ->
                val clienteId = backStackEntry.arguments?.getString("clienteId")?.toIntOrNull()
                if (clienteId != null) {
                    EditClientesScreen(
                        clienteId = clienteId,
                        onDrawerToggle = {
                            isDrawerVisible.value = !isDrawerVisible.value
                        },
                        goToCliente = {
                            navHostController.navigate(ScreenNavigation.ControlPanelClientes::class.java.simpleName)
                        }
                    )
                }
            }

            composable(ScreenNavigation.EliminarClientes::class.java.simpleName + "/{clienteId}") { backStackEntry ->
                val clienteId = backStackEntry.arguments?.getString("clienteId")?.toIntOrNull()
                if (clienteId != null) {
                    DeleteClientesScreen(
                        clienteId = clienteId,
                        onDrawerToggle = {
                            isDrawerVisible.value = !isDrawerVisible.value
                        },
                        goToCliente = {
                            navHostController.navigate(ScreenNavigation.ControlPanelClientes::class.java.simpleName) {
                                popUpTo(ScreenNavigation.ControlPanelClientes::class.java.simpleName) { inclusive = true }
                            }
                        }
                    )
                }
            }

            //Sistemas
            composable(ScreenNavigation.ControlPanelSistemas::class.java.simpleName) {
                IndexSistemasScreen(
                    onDrawerToggle = {
                        isDrawerVisible.value = !isDrawerVisible.value
                    },
                    goToSistema = {
                        navHostController.navigate(ScreenNavigation.ControlPanelSistemas::class.java.simpleName)
                    },
                    createSistema = {
                        navHostController.navigate(ScreenNavigation.CrearSistemas::class.java.simpleName)
                    },
                    editSistema = {
                        navHostController.navigate(ScreenNavigation.EditarSistemas::class.java.simpleName + "/${it}")
                    },
                    deleteSistema = {
                        navHostController.navigate(ScreenNavigation.EliminarSistemas::class.java.simpleName + "/${it}")
                    }
                )
            }

            composable(ScreenNavigation.CrearSistemas::class.java.simpleName) {
                CreateSistemasScreen(
                    onDrawerToggle = {
                        isDrawerVisible.value = !isDrawerVisible.value
                    },
                    goToSistema = {
                        navHostController.navigate(ScreenNavigation.ControlPanelSistemas::class.java.simpleName)
                    }
                )
            }

            composable(ScreenNavigation.EditarSistemas::class.java.simpleName + "/{sistemaId}") { backStackEntry ->
                val sistemaId = backStackEntry.arguments?.getString("sistemaId")?.toIntOrNull()
                if (sistemaId != null) {
                    EditSistemasScreen(
                        sistemaId = sistemaId,
                        onDrawerToggle = {
                            isDrawerVisible.value = !isDrawerVisible.value
                        },
                        goToSistema = {
                            navHostController.navigate(ScreenNavigation.ControlPanelSistemas::class.java.simpleName)
                        }
                    )
                }
            }

            composable(ScreenNavigation.EliminarSistemas::class.java.simpleName + "/{sistemaId}") { backStackEntry ->
                val sistemaId = backStackEntry.arguments?.getString("sistemaId")?.toIntOrNull()
                if (sistemaId != null) {
                    DeleteSistemasScreen(
                        sistemaId = sistemaId,
                        onDrawerToggle = {
                            isDrawerVisible.value = !isDrawerVisible.value
                        },
                        goToSistema = {
                            navHostController.navigate(ScreenNavigation.ControlPanelSistemas::class.java.simpleName) {
                                popUpTo(ScreenNavigation.ControlPanelSistemas::class.java.simpleName) { inclusive = true }
                            }
                        }
                    )
                }
            }

            //Tickets
            composable(ScreenNavigation.ControlPanelTickets::class.java.simpleName) {
                IndexTicketsScreen(
                    onDrawerToggle = {
                        isDrawerVisible.value = !isDrawerVisible.value
                    },
                    goToTiket = {
                        navHostController.navigate(ScreenNavigation.ControlPanelTickets::class.java.simpleName)
                    },
                    createTicket = {
                        navHostController.navigate(ScreenNavigation.CrearTickets::class.java.simpleName)
                    },
                    editTicket = {
                        navHostController.navigate(ScreenNavigation.EditarTickets::class.java.simpleName + "/${it}")
                    },
                    deleteTicket = {
                        navHostController.navigate(ScreenNavigation.EliminarTickets::class.java.simpleName + "/${it}")
                    }
                )
            }

            composable(ScreenNavigation.CrearTickets::class.java.simpleName) {
                CreateTicketsScreen(
                    onDrawerToggle = {
                        isDrawerVisible.value = !isDrawerVisible.value
                    },
                    goToTicket = {
                        navHostController.navigate(ScreenNavigation.ControlPanelTickets::class.java.simpleName)
                    }
                )
            }

            composable(ScreenNavigation.EditarTickets::class.java.simpleName + "/{ticketId}") { backStackEntry ->
                val ticketId = backStackEntry.arguments?.getString("ticketId")?.toIntOrNull()
                if (ticketId != null) {
                    EditTicketScreen(
                        ticketId = ticketId,
                        onDrawerToggle = {
                            isDrawerVisible.value = !isDrawerVisible.value
                        },
                        goToTicket = {
                            navHostController.navigate(ScreenNavigation.ControlPanelTickets::class.java.simpleName)
                        }
                    )
                }
            }

            composable(ScreenNavigation.EliminarTickets::class.java.simpleName + "/{ticketId}") { backStackEntry ->
                val ticketId = backStackEntry.arguments?.getString("ticketId")?.toIntOrNull()
                if (ticketId != null) {
                    DeleteTicketsScreen(
                        ticketId = ticketId,
                        onDrawerToggle = {
                            isDrawerVisible.value = !isDrawerVisible.value
                        },
                        goToTicket = {
                            navHostController.navigate(ScreenNavigation.ControlPanelTickets::class.java.simpleName) {
                                popUpTo(ScreenNavigation.ControlPanelTickets::class.java.simpleName) { inclusive = true }
                            }
                        }
                    )
                }
            }




        }

        NavigationDrawer(
            isVisible = isDrawerVisible.value,
            onItemClick = { itemTitle ->
                when (itemTitle) {
                    "Home" -> navHostController.navigate(ScreenNavigation.Home::class.java.simpleName)
                    "Prioridades" -> navHostController.navigate(ScreenNavigation.ControlPanelPrioridades::class.java.simpleName)
                    "Sistemas" -> navHostController.navigate(ScreenNavigation.ControlPanelSistemas::class.java.simpleName)
                    "Clientes" -> navHostController.navigate(ScreenNavigation.ControlPanelClientes::class.java.simpleName)
                    "Tickets" -> navHostController.navigate(ScreenNavigation.ControlPanelTickets::class.java.simpleName)
                }
                isDrawerVisible.value = false
            },
            onClose = {
                isDrawerVisible.value = false
            }
        )
    }
}
