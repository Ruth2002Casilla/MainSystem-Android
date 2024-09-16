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
import edu.ucne.composedemo.mainsystemtickets.data.local.dao.PrioridadDao
import edu.ucne.composedemo.mainsystemtickets.data.local.dao.SistemaDao
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.PrioridadEntity
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.SistemaEntity
import edu.ucne.composedemo.mainsystemtickets.presentation.component.NavigationDrawer
import edu.ucne.composedemo.mainsystemtickets.presentation.screenEntity.HomeScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.screenEntity.prioridades.CreatePrioridadesScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.screenEntity.prioridades.DeletePrioridadesScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.screenEntity.prioridades.EditPrioridadesScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.screenEntity.prioridades.IndexPrioridadesScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.screenEntity.sistemas.CreateSistemasScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.screenEntity.sistemas.DeleteSistemasScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.screenEntity.sistemas.EditSistemasScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.screenEntity.sistemas.IndexSistemasScreen
import kotlinx.coroutines.flow.Flow


@Composable
fun NavigationNavHost(
    navHostController: NavHostController,
    PrioridadesLista: Flow<List<PrioridadEntity>>,
    prioridadDao: PrioridadDao,
    SistemasLista: Flow<List<SistemaEntity>>,
    sistemaDao: SistemaDao,
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






        }







        NavigationDrawer(
            isVisible = isDrawerVisible.value,
            onItemClick = { itemTitle ->
                when (itemTitle) {
                    "Home" -> navHostController.navigate(ScreenNavigation.Home::class.java.simpleName)
                    "Prioridades" -> navHostController.navigate(ScreenNavigation.ControlPanelPrioridades::class.java.simpleName)
                    "Sistemas" -> navHostController.navigate(ScreenNavigation.ControlPanelSistemas::class.java.simpleName)
                }
                isDrawerVisible.value = false
            },
            onClose = {
                isDrawerVisible.value = false
            }
        )
    }
}
