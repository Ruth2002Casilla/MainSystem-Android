package edu.ucne.composedemo.mainsystemtickets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.composedemo.mainsystemtickets.data.local.database.QuikFixDb
import edu.ucne.composedemo.mainsystemtickets.presentation.navigation.NavigationNavHost
import edu.ucne.composedemo.mainsystemtickets.ui.theme.MainSystemTicketsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var quikFixDb: QuikFixDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        quikFixDb = Room.databaseBuilder(
            applicationContext,
            QuikFixDb::class.java,
            "QuikFixDb.db"
        ).build()

        enableEdgeToEdge()

        val prioridadDao = quikFixDb.prioridadDao()
        val sistemaDao = quikFixDb.sistemaDao()
        val clienteDao = quikFixDb.clienteDao()
        val ticketDao = quikFixDb.ticketDao()

        setContent {
            MainSystemTicketsTheme {
                val navController = rememberNavController()

                NavigationNavHost(
                    navHostController = navController,
                    PrioridadesLista = prioridadDao.getAll(),
                    prioridadDao = prioridadDao,
                    SistemasLista = sistemaDao.getAll(),
                    sistemaDao = sistemaDao,
                    ClientesLista = clienteDao.getAll(),
                    clienteDao = clienteDao,
                    ticketDao = ticketDao,
                    TicketsLista = ticketDao.getAll()
                )
            }
        }
    }
}