package edu.ucne.composedemo.mainsystemtickets.presentation.screenEntity.clientes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.composedemo.mainsystemtickets.R
import edu.ucne.composedemo.mainsystemtickets.ui.theme.Black
import edu.ucne.composedemo.mainsystemtickets.ui.theme.bluecustom

@Composable
fun CreateClientesScreen(
    viewModel: ClienteViewModel = hiltViewModel(),
    onDrawerToggle: () -> Unit,
    goToCliente: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    BodyCreateClientes(
        uiState = uiState,
        onNombreChange = viewModel::onNombreChange,
        onRNCChange = viewModel::onRNCChange,
        onTelefonoChange = viewModel::onTelefonoChange,
        onCelularChange = viewModel::onCelularChange,
        onEmailChange = viewModel::onEmailChange,
        onDireccionChange = viewModel::onDireccionChange,
        onDrawerToggle = onDrawerToggle,
        goToCliente = goToCliente,
        saveCliente = viewModel::save
    )
}

@Composable
fun BodyCreateClientes(
    uiState: UiState,
    onDrawerToggle: () -> Unit,
    onNombreChange: (String) -> Unit,
    onRNCChange: (String) -> Unit,
    onTelefonoChange: (String) -> Unit,
    onCelularChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onDireccionChange: (String) -> Unit,
    goToCliente: () -> Unit,
    saveCliente: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.mipmap.cliente),
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
                             .padding(top = 10.dp)
                            .border(BorderStroke(2.dp, bluecustom), shape = RoundedCornerShape(10.dp))
                    ) {
                        BasicTextField(
                            value = uiState.nombre ?: "",
                            onValueChange = onNombreChange,
                            textStyle = TextStyle(
                                color = Black,
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
                                visible = uiState.nombre.isNullOrEmpty(),
                                enter = fadeIn(),
                                exit = fadeOut(),
                                modifier = Modifier
                                    .offset(y = 14.dp)
                            ) {
                                Text(
                                    text = "Nombre",
                                    color = Color.Gray,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(BorderStroke(2.dp, bluecustom), shape = RoundedCornerShape(10.dp))
                    ) {
                        BasicTextField(
                            value = uiState.RNC ?: "",
                            onValueChange = onRNCChange,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            textStyle = TextStyle(
                                color = Black,
                                fontSize = 16.sp,
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
                                visible = uiState.RNC.isNullOrEmpty(),
                                enter = fadeIn(),
                                exit = fadeOut(),
                                modifier = Modifier
                                    .offset(y = 14.dp)
                            ) {
                                Text(
                                    text = "RNC",
                                    color = Color.Gray,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(BorderStroke(2.dp, bluecustom), shape = RoundedCornerShape(10.dp))
                    ) {
                        BasicTextField(
                            value = uiState.telefono ?: "",
                            onValueChange = onTelefonoChange,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                            textStyle = TextStyle(
                                color = Black,
                                fontSize = 16.sp,
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
                                visible = uiState.telefono.isNullOrEmpty(),
                                enter = fadeIn(),
                                exit = fadeOut(),
                                modifier = Modifier
                                    .offset(y = 14.dp)
                            ) {
                                Text(
                                    text = "Teléfono",
                                    color = Color.Gray,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(BorderStroke(2.dp, bluecustom), shape = RoundedCornerShape(10.dp))
                    ) {
                        BasicTextField(
                            value = uiState.celular ?: "",
                            onValueChange = onCelularChange,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                            textStyle = TextStyle(
                                color = Black,
                                fontSize = 16.sp,
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
                                visible = uiState.celular.isNullOrEmpty(),
                                enter = fadeIn(),
                                exit = fadeOut(),
                                modifier = Modifier
                                    .offset(y = 14.dp)
                            ) {
                                Text(
                                    text = "Celular",
                                    color = Color.Gray,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(BorderStroke(2.dp, bluecustom), shape = RoundedCornerShape(10.dp))
                    ) {
                        BasicTextField(
                            value = uiState.email ?: "",
                            onValueChange = onEmailChange,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                            textStyle = TextStyle(
                                color = Black,
                                fontSize = 16.sp,
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
                                visible = uiState.email.isNullOrEmpty(),
                                enter = fadeIn(),
                                exit = fadeOut(),
                                modifier = Modifier
                                    .offset(y = 14.dp)
                            ) {
                                Text(
                                    text = "Email",
                                    color = Color.Gray,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(BorderStroke(2.dp, bluecustom), shape = RoundedCornerShape(10.dp))
                    ) {
                        BasicTextField(
                            value = uiState.direccion ?: "",
                            onValueChange = onDireccionChange,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                            textStyle = TextStyle(
                                color = Black,
                                fontSize = 16.sp,
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
                                visible = uiState.direccion.isNullOrEmpty(),
                                enter = fadeIn(),
                                exit = fadeOut(),
                                modifier = Modifier
                                    .offset(y = 14.dp)
                            ) {
                                Text(
                                    text = "Dirección",
                                    color = Color.Gray,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }

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
                            saveCliente()
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
                                contentDescription = "Guardar",
                                tint = White,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Guardar",
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
                    goToCliente()
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCreateClienteScreen() {
    val testUiState = UiState(
        nombre = "",
        RNC = "",
        telefono = "",
        celular = "",
        email = "",
        direccion = "",
        errorMessage = null,
        guardado = false
    )

    val onDrawerToggle: () -> Unit = {}
    val onNombreChange: (String) -> Unit = {}
    val onRNCChange: (String) -> Unit = {}
    val onEmailChange: (String) -> Unit = {}
    val onCelularChange: (String) -> Unit = {}
    val onTelefonoChange: (String) -> Unit = {}
    val onDireccionChange: (String) -> Unit = {}
    val goToCliente: () -> Unit = {}
    val saveCliente: () -> Unit = {}

    BodyCreateClientes(
        uiState = testUiState,
        onDrawerToggle = onDrawerToggle,
        onNombreChange = onNombreChange,
        onRNCChange = onRNCChange,
        onEmailChange = onEmailChange,
        onCelularChange = onCelularChange,
        onTelefonoChange = onTelefonoChange,
        onDireccionChange = onDireccionChange,
        goToCliente = goToCliente,
        saveCliente = saveCliente
    )
}

