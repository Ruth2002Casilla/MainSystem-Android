package edu.ucne.composedemo.mainsystemtickets.presentation.tickets

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.composedemo.mainsystemtickets.R
import edu.ucne.composedemo.mainsystemtickets.ui.theme.Black
import edu.ucne.composedemo.mainsystemtickets.ui.theme.bluecustom
import java.util.Calendar

@Composable
fun EditTicketScreen(
    viewModel: TicketViewModel = hiltViewModel(),
    ticketId: Int?,
    onDrawerToggle: () -> Unit,
    goToTicket: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = true) {
        if (ticketId != null) {
            viewModel.selectedTicket(ticketId)
        }
    }

    BodyEditTickets(
        uiState = uiState,
        onAsuntoChange = viewModel::onAsuntoChange,
        onDescripcionChange = viewModel::onDescripcionChange,
        onPrioridadChange = viewModel::onPrioridadIdChange,
        onClienteChange = viewModel::onClienteChange,
        onSistemaChange = viewModel::onSistemaObChange,
        onFechaChange = viewModel::onFechaChange,
        onDrawerToggle = onDrawerToggle,
        goToTicket = goToTicket,
        updateTicket = viewModel::update
    )
}

@Composable
fun BodyEditTickets(
    uiState: UiState,
    onDrawerToggle: () -> Unit,
    onClienteChange: (String) -> Unit,
    onAsuntoChange: (String) -> Unit,
    onDescripcionChange: (String) -> Unit,
    onPrioridadChange: (Int) -> Unit,
    onFechaChange: (String) -> Unit,
    goToTicket: () -> Unit,
    updateTicket: () -> Unit,
    onSistemaChange: (String) -> Unit,

    ) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.mipmap.tickets),
            contentDescription = "Background Principal",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 50.dp)
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .border(3.dp, bluecustom, shape = RoundedCornerShape(30.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = White
                ),
                shape = RoundedCornerShape(30.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                BorderStroke(2.dp, bluecustom),
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        BasicTextField(
                            value = uiState.asunto ?: "",
                            onValueChange = onAsuntoChange,
                            textStyle = TextStyle(
                                color = Green,
                                fontSize = 16.sp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 22.dp)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, top = 8.dp)
                        ) {
                            this@Column.AnimatedVisibility(
                                visible = uiState.asunto.isNullOrEmpty(),
                                enter = fadeIn(),
                                exit = fadeOut(),
                                modifier = Modifier
                                    .offset(y = 14.dp)
                            ) {
                                Text(
                                    text = "Asunto",
                                    color = Color.Gray,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                BorderStroke(2.dp, bluecustom),
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        BasicTextField(
                            value = uiState.descripcion ?: "",
                            onValueChange = onDescripcionChange,
                            textStyle = TextStyle(
                                color = Green,
                                fontSize = 16.sp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 22.dp)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, top = 8.dp)
                        ) {
                            this@Column.AnimatedVisibility(
                                visible = uiState.descripcion.isNullOrEmpty(),
                                enter = fadeIn(),
                                exit = fadeOut(),
                                modifier = Modifier
                                    .offset(y = 14.dp)
                            ) {
                                Text(
                                    text = "Descripción",
                                    color = Color.Gray,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }

                    CustomSelectEditarPrioridades(uiState, onPrioridadChange)
                    CustomSelectSistemaEdit(uiState, onSistemaChange = {})
                    CustomSelectClienteEdit(uiState, onClienteChange = {})
                    CustomDatePickerEditar(uiState, onFechaChange)

                    uiState.errorMessage?.let { message ->
                        Text(
                            text = message,
                            color = Color.Red,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    Button(
                        onClick = {
                            updateTicket()
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = bluecustom
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Actualizar",
                                tint = White,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Actualizar",
                                color = White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
            LaunchedEffect(uiState.guardado) {
                if (uiState.guardado == true) {
                    goToTicket()
                }
            }
        }
    }
}

@Composable
fun CustomSelectEditarPrioridades(
    uiState: UiState,
    onPrioridadChange: (Int) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val selectedPrioridad = uiState.prioridades.find { it.prioridadId == uiState.prioridadId }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(BorderStroke(2.dp, bluecustom), shape = RoundedCornerShape(10.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded.value = !expanded.value }
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedPrioridad?.descripcion ?: "",
                style = TextStyle(
                    color = Green,
                    fontSize = 16.sp
                ),
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Dropdown Icon",
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterVertically)
                    .offset(x = 8.dp),
                tint = Color.Gray
            )
        }


        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier
                .width(265.dp)
                .background(Color.White)
                .border(BorderStroke(2.dp, bluecustom), shape = RoundedCornerShape(10.dp))
                .align(Alignment.TopStart)
        ) {
            uiState.prioridades.forEach { prioridad ->
                DropdownMenuItem(
                    onClick = {
                        onPrioridadChange(prioridad.prioridadId ?: 0)
                        expanded.value = false
                        Toast.makeText(context, prioridad.descripcion, Toast.LENGTH_SHORT).show()
                    },
                    text = {
                        Text(text = prioridad.descripcion)
                    }
                )
            }
        }

        AnimatedVisibility(
            visible = selectedPrioridad == null,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp)
                .offset(y = 14.dp)
        ) {
            Text(
                text = "Prioridad",
                color = Color.Gray,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun CustomSelectSistemaEdit(
    uiState: UiState,
    onSistemaChange: (Int) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val selectedSistema = uiState.sistemas.find { it.sistemaId == uiState.sistemaId }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(BorderStroke(2.dp, Color.Blue), shape = RoundedCornerShape(10.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded.value = !expanded.value }
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedSistema?.nombre ?: "",
                style = TextStyle(
                    color = Color.Green,
                    fontSize = 16.sp
                ),
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Dropdown Icon",
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterVertically)
                    .offset(x = 8.dp),
                tint = Color.Gray
            )
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier
                .width(265.dp)
                .background(Color.White)
                .border(BorderStroke(2.dp, Color.Blue), shape = RoundedCornerShape(10.dp))
                .align(Alignment.TopStart)
        ) {
            uiState.sistemas.forEach { sistema ->
                DropdownMenuItem(
                    onClick = {
                        sistema.sistemaId?.let { id ->
                            onSistemaChange(id)
                        }
                        expanded.value = false
                        Toast.makeText(context, sistema.nombre, Toast.LENGTH_SHORT).show()
                    },
                    text = {
                        Text(text = sistema.nombre)
                    }
                )
            }
        }

        AnimatedVisibility(
            visible = selectedSistema == null,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp)
                .offset(y = 14.dp)
        ) {
            Text(
                text = "Sistema",
                color = Color.Gray,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun CustomSelectClienteEdit(
    uiState: UiState,
    onClienteChange: (Int) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val selectedCliente = uiState.clientes.find { it.clienteId == uiState.clienteId }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(BorderStroke(2.dp, Color.Blue), shape = RoundedCornerShape(10.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded.value = !expanded.value }
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedCliente?.nombre ?: "Cliente",
                style = TextStyle(
                    color = Color.Green,
                    fontSize = 16.sp
                ),
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Dropdown Icon",
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterVertically)
                    .offset(x = 8.dp),
                tint = Color.Gray
            )
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier
                .width(265.dp)
                .background(Color.White)
                .border(BorderStroke(2.dp, Color.Blue), shape = RoundedCornerShape(10.dp))
                .align(Alignment.TopStart)
        ) {
            uiState.clientes.forEach { cliente ->
                DropdownMenuItem(
                    onClick = {
                        cliente.clienteId?.let { id ->
                            onClienteChange(id)
                        }
                        expanded.value = false
                        Toast.makeText(context, cliente.nombre ?: "Cliente", Toast.LENGTH_SHORT).show()
                    },
                    text = {
                        Text(text = cliente.nombre ?: "Cliente")
                    }
                )
            }
        }

        AnimatedVisibility(
            visible = selectedCliente == null,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp)
                .offset(y = 14.dp)
        ) {
            Text(
                text = "Cliente",
                color = Color.Gray,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun CustomDatePickerEditar(
    uiState: UiState,
    onFechaChange: (String) -> Unit
) {
    val context = LocalContext.current
    val expanded = remember { mutableStateOf(false) }
    val selectedDate = uiState.fecha ?: ""

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(BorderStroke(2.dp, bluecustom), shape = RoundedCornerShape(10.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expanded.value = true
                }
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (selectedDate.isEmpty()) "" else selectedDate,
                style = TextStyle(
                    color = if (selectedDate.isEmpty()) Color.Gray else Green,
                    fontSize = 16.sp
                ),
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = "Calendar Icon",
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterVertically)
                    .offset(x = 8.dp),
                tint = Color.Gray
            )
        }

        if (expanded.value) {
            val datePickerDialog = DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    val selected = "$dayOfMonth/${month + 1}/$year"
                    onFechaChange(selected)
                    expanded.value = false
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        AnimatedVisibility(
            visible = selectedDate.isEmpty(),
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp)
                .offset(y = 14.dp)
        ) {
            Text(
                text = "Fecha",
                color = Color.Gray,
                fontSize = 16.sp
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditTicketScreenPreview() {
    val testUiState = UiState(
        asunto = "",
        descripcion = "",
        cliente = "",
        prioridadId = 0,
        sistemaId = 0,
        fecha = "",
        errorMessage = null,
        guardado = false
    )

    val onDrawerToggle: () -> Unit = {}
    val onClienteChange: (String) -> Unit = {}
    val onAsuntoChange: (String) -> Unit = {}
    val onDescripcionChange: (String) -> Unit = {}
    val onPrioridadChange: (Int) -> Unit = {}
    val onFechaChange: (String) -> Unit = {}
    val goToTicket: () -> Unit = {}
    val updateTicket: () -> Unit = {}
    val onSistemaChange: (String) -> Unit = {}

    BodyEditTickets(
        uiState = testUiState,
        onDrawerToggle = onDrawerToggle,
        onClienteChange = onClienteChange,
        onAsuntoChange = onAsuntoChange,
        onDescripcionChange = onDescripcionChange,
        onPrioridadChange = onPrioridadChange,
        onFechaChange = onFechaChange,
        goToTicket = goToTicket,
        updateTicket = updateTicket,
        onSistemaChange = onSistemaChange
    )
}

