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
        NavHost(navController = navHostController, startDestination = Screen.Home) {

            composable<Screen.Home>{
                HomeScreen(
                    onDrawerToggle = { isDrawerVisible.value = !isDrawerVisible.value }
                )
            }
                //Prioridades
            composable<Screen.ControlPanelPrioridades> {
                IndexPrioridadesScreen(
                    onDrawerToggle = {
                        isDrawerVisible.value = !isDrawerVisible.value
                    },
                    goToPrioridad = {
                        navHostController.navigate(Screen.ControlPanelPrioridades)
                    },
                    createPrioridad = {
                        navHostController.navigate(Screen.CrearPrioridades)
                    },
                    editPrioridad = {
                        navHostController.navigate(Screen.EditarPrioridades(it))
                    },
                    deletePrioridad = {
                        navHostController.navigate(Screen.EliminarPrioridades(it))
                    }
                )
            }

            composable<Screen.CrearPrioridades> {
                CreatePrioridadesScreen(
                    onDrawerToggle = {
                        isDrawerVisible.value = !isDrawerVisible.value
                    },
                    goToPrioridad = {
                        navHostController.navigate(Screen.ControlPanelPrioridades)
                    }
                )
            }

            composable<Screen.EditarPrioridades> { backStackEntry ->
                val prioridadId = backStackEntry.arguments?.getInt("prioridadId")
                if (prioridadId != null) {
                    EditPrioridadesScreen(
                        prioridadId = prioridadId,
                        onDrawerToggle = {
                            isDrawerVisible.value = !isDrawerVisible.value
                        },
                        goToPrioridad = {
                            navHostController.navigate(Screen.ControlPanelPrioridades)
                        }
                    )
                }
            }

            composable<Screen.EliminarPrioridades> { backStackEntry ->
                val prioridadId = backStackEntry.arguments?.getInt("prioridadId")
                if (prioridadId != null) {
                    DeletePrioridadesScreen(
                        prioridadId = prioridadId,
                        onDrawerToggle = {
                            isDrawerVisible.value = !isDrawerVisible.value
                        },
                        goToPrioridad = {
                            navHostController.navigate(Screen.ControlPanelPrioridades) {
                                popUpTo(Screen.ControlPanelPrioridades) { inclusive = true }
                            }
                        }
                    )
                }
            }


            //Clientes
            composable<Screen.ControlPanelClientes> {
                IndexClientesScreen(
                    onDrawerToggle = {
                        isDrawerVisible.value = !isDrawerVisible.value
                    },
                    goToCliente = {
                        navHostController.navigate(Screen.ControlPanelClientes)
                    },
                    createCliente = {
                        navHostController.navigate(Screen.CrearClientes)
                    },
                    editCliente = {
                        navHostController.navigate(Screen.EditarClientes(it))
                    },
                    deleteCliente = {
                        navHostController.navigate(Screen.EliminarClientes(it))
                    }
                )
            }

            composable<Screen.CrearClientes> {
                CreateClientesScreen(
                    onDrawerToggle = {
                        isDrawerVisible.value = !isDrawerVisible.value
                    },
                    goToCliente = {
                        navHostController.navigate(Screen.ControlPanelClientes)
                    }
                )
            }

            composable<Screen.EditarClientes> { backStackEntry ->
                val clienteId = backStackEntry.arguments?.getInt("clienteId")
                if (clienteId != null) {
                    EditClientesScreen(
                        clienteId = clienteId,
                        onDrawerToggle = {
                            isDrawerVisible.value = !isDrawerVisible.value
                        },
                        goToCliente = {
                            navHostController.navigate(Screen.ControlPanelClientes)
                        }
                    )
                }
            }

            composable<Screen.EliminarClientes> { backStackEntry ->
                val clienteId = backStackEntry.arguments?.getInt("clienteId")
                if (clienteId != null) {
                    DeleteClientesScreen(
                        clienteId = clienteId,
                        onDrawerToggle = {
                            isDrawerVisible.value = !isDrawerVisible.value
                        },
                        goToCliente = {
                            navHostController.navigate(Screen.ControlPanelClientes) {
                                popUpTo(Screen.ControlPanelClientes) { inclusive = true }
                            }
                        }
                    )
                }
            }

            //Sistemas
            composable<Screen.ControlPanelSistemas> {
                IndexSistemasScreen(
                    onDrawerToggle = {
                        isDrawerVisible.value = !isDrawerVisible.value
                    },
                    goToSistema = {
                        navHostController.navigate(Screen.ControlPanelSistemas)
                    },
                    createSistema = {
                        navHostController.navigate(Screen.CrearSistemas)
                    },
                    editSistema = {
                        navHostController.navigate(Screen.EditarSistemas(it))
                    },
                    deleteSistema = {
                        navHostController.navigate(Screen.EliminarSistemas(it))
                    }
                )
            }

            composable<Screen.CrearSistemas> {
                CreateSistemasScreen(
                    onDrawerToggle = {
                        isDrawerVisible.value = !isDrawerVisible.value
                    },
                    goToSistema = {
                        navHostController.navigate(Screen.ControlPanelSistemas)
                    }
                )
            }

            composable<Screen.EditarSistemas> { backStackEntry ->
                val sistemaId = backStackEntry.arguments?.getInt("sistemaId")
                if (sistemaId != null) {
                    EditSistemasScreen(
                        sistemaId = sistemaId,
                        onDrawerToggle = {
                            isDrawerVisible.value = !isDrawerVisible.value
                        },
                        goToSistema = {
                            navHostController.navigate(Screen.ControlPanelSistemas)
                        }
                    )
                }
            }

            composable<Screen.EliminarSistemas> { backStackEntry ->
                val sistemaId = backStackEntry.arguments?.getInt("sistemaId")
                if (sistemaId != null) {
                    DeleteSistemasScreen(
                        sistemaId = sistemaId,
                        onDrawerToggle = {
                            isDrawerVisible.value = !isDrawerVisible.value
                        },
                        goToSistema = {
                            navHostController.navigate(Screen.ControlPanelSistemas) {
                                popUpTo(Screen.ControlPanelSistemas) { inclusive = true }
                            }
                        }
                    )
                }
            }

            //Tickets
            composable<Screen.ControlPanelTickets> {
                IndexTicketsScreen(
                    onDrawerToggle = {
                        isDrawerVisible.value = !isDrawerVisible.value
                    },
                    goToTiket = {
                        navHostController.navigate(Screen.ControlPanelTickets)
                    },
                    createTicket = {
                        navHostController.navigate(Screen.CrearTickets)
                    },
                    editTicket = {
                        navHostController.navigate(Screen.EditarTickets(it))
                    },
                    deleteTicket = {
                        navHostController.navigate(Screen.EliminarTickets(it))
                    }
                )
            }

            composable<Screen.CrearTickets> {
                CreateTicketsScreen(
                    onDrawerToggle = {
                        isDrawerVisible.value = !isDrawerVisible.value
                    },
                    goToTicket = {
                        navHostController.navigate(Screen.ControlPanelTickets)
                    }
                )
            }

            composable<Screen.EditarTickets> { backStackEntry ->
                val ticketId = backStackEntry.arguments?.getInt("ticketId")
                if (ticketId != null) {
                    EditTicketScreen(
                        ticketId = ticketId,
                        onDrawerToggle = {
                            isDrawerVisible.value = !isDrawerVisible.value
                        },
                        goToTicket = {
                            navHostController.navigate(Screen.ControlPanelTickets)
                        }
                    )
                }
            }

            composable<Screen.EliminarTickets> { backStackEntry ->
                val ticketId = backStackEntry.arguments?.getInt("ticketId")
                if (ticketId != null) {
                    DeleteTicketsScreen(
                        ticketId = ticketId,
                        onDrawerToggle = {
                            isDrawerVisible.value = !isDrawerVisible.value
                        },
                        goToTicket = {
                            navHostController.navigate(Screen.ControlPanelTickets) {
                                popUpTo(Screen.ControlPanelTickets) { inclusive = true }
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
                    "Home" -> navHostController.navigate(Screen.Home)
                    "Prioridades" -> navHostController.navigate(Screen.ControlPanelPrioridades)
                    "Sistemas" -> navHostController.navigate(Screen.ControlPanelSistemas)
                    "Clientes" -> navHostController.navigate(Screen.ControlPanelClientes)
                    "Tickets" -> navHostController.navigate(Screen.ControlPanelTickets)
                }
                isDrawerVisible.value = false
            },
            onClose = {
                isDrawerVisible.value = false
            }
        )
    }
}
