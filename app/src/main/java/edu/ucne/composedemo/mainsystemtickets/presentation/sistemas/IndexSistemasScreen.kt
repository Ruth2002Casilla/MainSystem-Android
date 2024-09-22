package edu.ucne.composedemo.mainsystemtickets.presentation.sistemas

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
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.SistemaEntity
import edu.ucne.composedemo.mainsystemtickets.ui.theme.bluecustom

@Composable
fun IndexSistemasScreen(
    viewModel: SistemaViewModel = hiltViewModel(),
    onDrawerToggle: () -> Unit,
    goToSistema: () -> Unit,
    createSistema: () -> Unit,
    editSistema: (Int) -> Unit,
    deleteSistema: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.mipmap.sistemas),
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
            if (uiState.sistemas.isEmpty()) {
                MensajePersonalizado()
            } else {
                SistemasList(
                    sistemas = uiState.sistemas,
                    onEditClick = { sistema ->
                        sistema.sistemaId?.let { id ->
                            editSistema(id)
                        }
                    },
                    onDeleteClick = { sistema ->
                        sistema.sistemaId?.let { id ->
                            deleteSistema(id)
                        }
                    }
                )
            }
        }

        FloatingActionButton(
            onClick = createSistema,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 60.dp, end = 16.dp),
            containerColor = bluecustom,
            contentColor = White
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Agregar Sistema"
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
fun SistemasList(
    sistemas: List<SistemaEntity>,
    onEditClick: (SistemaEntity) -> Unit,
    onDeleteClick: (SistemaEntity) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 0.dp)
    ) {
        items(sistemas) { sistema ->
            SistemaCard(
                sistema = sistema,
                onEditClick = onEditClick,
                onDeleteClick = onDeleteClick,
                index = sistemas.indexOf(sistema) + 1
            )
        }
    }
}

@Composable
fun SistemaCard(
    sistema: SistemaEntity,
    onEditClick: (SistemaEntity) -> Unit,
    onDeleteClick: (SistemaEntity) -> Unit,
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
                    text = sistema.nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = White
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(onClick = { onEditClick(sistema) }) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_edit_24),
                        contentDescription = "Editar",
                        modifier = Modifier.size(32.dp)
                    )
                }
                IconButton(onClick = { onDeleteClick(sistema) }) {
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
                contentDescription = "NO SE ENCONTRARON SISTEMAS",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "NO SE ENCONTRARON SISTEMAS",
                color = Color.Gray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IndexSistemasScreenPreview() {
    val sistema = SistemaEntity(
        sistemaId = 0,
        nombre = ""
    )

    val onEditClick: (SistemaEntity) -> Unit = { }
    val onDeleteClick: (SistemaEntity) -> Unit = { }

    Image(
        painter = painterResource(id = R.mipmap.sistemas),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    SistemaCard(
        sistema = sistema,
        onEditClick = onEditClick,
        onDeleteClick = onDeleteClick,
        index = 1
    )
}

