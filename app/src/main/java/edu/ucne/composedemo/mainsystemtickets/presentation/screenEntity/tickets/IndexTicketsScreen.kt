package edu.ucne.composedemo.mainsystemtickets.presentation.screenEntity.tickets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.composedemo.mainsystemtickets.R
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.TicketEntity
import edu.ucne.composedemo.mainsystemtickets.ui.theme.bluecustom

@Composable
fun IndexTicketsScreen(
    viewModel: TicketViewModel = hiltViewModel(),
    onDrawerToggle: () -> Unit,
    goToTiket: () -> Unit,
    createTicket: () -> Unit,
    editTicket: (Int) -> Unit,
    deleteTicket: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.mipmap.tickets),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Button(
            onClick = onDrawerToggle,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 50.dp, start = 5.dp)
                .background(Color.Transparent),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_menu_open_24),
                    contentDescription = "Menu Icon",
                    tint = White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 280.dp)
        ) {
            if (uiState.tickets.isEmpty()) {
                MensajePersonalizado()
            } else {
                TicketsList(
                    tickets = uiState.tickets,
                    onEditClick = { ticket ->
                        ticket.ticketId?.let { id ->
                            editTicket(id)
                        }
                    },
                    onDeleteClick = { ticket ->
                        ticket.ticketId?.let { id ->
                            deleteTicket(id)
                        }
                    }
                )
            }
        }

        FloatingActionButton(
            onClick = createTicket,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 60.dp, end = 16.dp),
            containerColor = bluecustom,
            contentColor = White
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Agregar Ticket"
            )
        }

        uiState.errorMessage?.let { message ->
            Text(
                text = message,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun TicketsList(
    tickets: List<TicketEntity>,
    onEditClick: (TicketEntity) -> Unit,
    onDeleteClick: (TicketEntity) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 0.dp)
    ) {
        items(tickets) { ticket ->
            TicketCard(
                ticket = ticket,
                onEditClick = onEditClick,
                onDeleteClick = onDeleteClick,
                index = tickets.indexOf(ticket) + 1
            )
        }
    }
}

@Composable
fun TicketCard(
    ticket: TicketEntity,
    onEditClick: (TicketEntity) -> Unit,
    onDeleteClick: (TicketEntity) -> Unit,
    index: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 10.dp,
                end = 10.dp,
                bottom = 4.dp
            ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = bluecustom)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "$index.",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = White,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = ticket.asunto,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = White
                )
                Text(
                    text = ticket.fecha.toString(),
                    fontSize = 14.sp,
                    color = White
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(onClick = { onEditClick(ticket) }) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_edit_24),
                        contentDescription = "Editar",
                        modifier = Modifier.size(32.dp)
                    )
                }
                IconButton(onClick = { onDeleteClick(ticket) }) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_delete_forever_24),
                        contentDescription = "Eliminar",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MensajePersonalizado() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = "NO SE ENCONTRARON TICKETS",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "NO SE ENCONTRARON TICKETS",
                color = Color.Gray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IndexTicketsScreenPreview() {
    val ticket = TicketEntity(
        ticketId = 0,
        prioridadId = 0,
        sistemaId = 0,
        fecha = "",
        clienteId = 4,
        asunto = "",
        descripcion = ""
    )
    val onEditClick: (TicketEntity) -> Unit = { }
    val onDeleteClick: (TicketEntity) -> Unit = { }

    Image(
        painter = painterResource(id = R.mipmap.tickets),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    TicketCard(
        ticket = ticket,
        onEditClick = onEditClick,
        onDeleteClick = onDeleteClick,
        index = 1
    )
}

