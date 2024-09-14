package edu.ucne.composedemo.mainsystemtickets.presentation.screen.prioridades

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.ucne.composedemo.mainsystemtickets.R
import androidx.compose.ui.Alignment
import edu.ucne.composedemo.mainsystemtickets.presentation.navigation.menu.AppMenu

@Composable
fun ScreenListPrioridades() {
    val expandedState = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.mipmap.ico_entities),
            contentDescription = "Full Screen Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(id = R.string.priori),
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.custom_blue),
            modifier = Modifier
                .padding(horizontal = 11.dp)
                .offset(y = 20.dp)
                .offset(x = 120.dp)
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .offset(y = 130.dp)
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.custom_blue))
                    .padding(vertical = 8.dp),
                     horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "ID",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .weight(1f)
                        .offset(x = 10.dp, y = 5.dp)
                )
                Text(
                    text = "Descripción",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .weight(3f)
                        .offset(x = 10.dp, y = 5.dp)
                )
                Text(
                    text = "Días Compromiso",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .weight(2f)
                        .offset(x = 10.dp, y = 2.dp)
                )
            }

            // Celdas de la tabla
//            LazyColumn {
//                items(/**/) { item ->
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(colorResource(id = R.color.custom_lightBlue))
//                            .padding(vertical = 8.dp),
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Text(
//                            text = item.id.toString(),
//                            modifier = Modifier.weight(1f),
//                            fontSize = 14.sp,
//                            fontFamily = FontFamily.Serif
//                        )
//                        Text(
//                            text = item.descripcion,
//                            modifier = Modifier.weight(3f),
//                            fontSize = 14.sp,
//                            fontFamily = FontFamily.Serif
//                        )
//                        Text(
//                            text = item.diasCompromiso.toString(),
//                            modifier = Modifier.weight(2f),
//                            fontSize = 14.sp,
//                            fontFamily = FontFamily.Serif
//                        )
//                    }
//
//                }
//            }
        }
        FloatingActionButtonWithPlus(onClick = {
            // Acción que desea
        })
        AppMenu(expandedState, iconTintColor = Color.Black)
    }
}

@Composable
fun FloatingActionButtonWithPlus(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color.White,
        contentColor = Color.Blue,
        modifier = Modifier
            .padding(16.dp)
            .offset(y = 730.dp)
            .offset(x = 170.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_add_24),
            contentDescription = "Add",
            tint = Color.Black,
            modifier = Modifier.size(70.dp)
        )
    }

}


@Preview(showBackground = true)
@Composable
fun ScreenListPrioridadesPreview() {
    ScreenListPrioridades()
}
