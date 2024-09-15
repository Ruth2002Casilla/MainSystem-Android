package com.aarondeveloper.ticketing.presentation.navigation

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
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.PrioridadEntity
import edu.ucne.composedemo.mainsystemtickets.presentation.component.NavigationDrawer
import edu.ucne.composedemo.mainsystemtickets.presentation.navigation.ScreenNavigation
import edu.ucne.composedemo.mainsystemtickets.presentation.screenEntity.HomeScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.screenEntity.prioridades.CreatePrioridadesScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.screenEntity.prioridades.DeletePrioridadesScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.screenEntity.prioridades.EditPrioridadesScreen
import edu.ucne.composedemo.mainsystemtickets.presentation.screenEntity.prioridades.IndexPrioridadesScreen
import kotlinx.coroutines.flow.Flow


@Composable
fun NavigationNavHost(
    navHostController: NavHostController,
    PrioridadesLista: Flow<List<PrioridadEntity>>,
    prioridadDao: PrioridadDao
) {
    val isDrawerVisible = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navHostController, startDestination = ScreenNavigation.Home::class.java.simpleName) {

            composable(ScreenNavigation.Home::class.java.simpleName) {
                HomeScreen(
                    onDrawerToggle = { isDrawerVisible.value = !isDrawerVisible.value }
                )
            }

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

        }

        NavigationDrawer(
            isVisible = isDrawerVisible.value,
            onItemClick = { itemTitle ->
                when (itemTitle) {
                    "Home" -> navHostController.navigate(ScreenNavigation.Home::class.java.simpleName)
                    "Prioridades" -> navHostController.navigate(ScreenNavigation.ControlPanelPrioridades::class.java.simpleName)
                }
                isDrawerVisible.value = false
            },
            onClose = {
                isDrawerVisible.value = false
            }
        )
    }
}
