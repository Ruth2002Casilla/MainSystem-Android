package edu.ucne.composedemo.mainsystemtickets.presentation.component

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import edu.ucne.composedemo.mainsystemtickets.R
import edu.ucne.composedemo.mainsystemtickets.ui.theme.PurpleGrey40
import edu.ucne.composedemo.mainsystemtickets.ui.theme.bluecustom


@Composable
fun NavigationDrawer(
    isVisible: Boolean,
    onItemClick: (String) -> Unit,
    onClose: () -> Unit
) {
    if (isVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
                .clickable { onClose() }
        )
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(durationMillis = 300)) + slideInHorizontally(
            initialOffsetX = { -it },
            animationSpec = tween(durationMillis = 300)
        ),
        exit = fadeOut(animationSpec = tween(durationMillis = 300)) + slideOutHorizontally(
            targetOffsetX = { -it },
            animationSpec = tween(durationMillis = 300)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(280.dp)
                .background(Color.White)
        ) {
            Column {
                DrawerHeader(onClose = onClose)
                DrawerBody(
                    items = listOf(
                        MenuItem("Home", Icons.Default.Home),
                        MenuItem("Prioridades", Icons.Default.Favorite),
                        MenuItem("Tickets", Icons.Default.Star),
                        MenuItem("Clientes", Icons.Default.Person),
                        MenuItem("Sistemas", Icons.Default.Settings),
                        MenuItem("Información", Icons.Default.Info)
                    ),
                    onItemClick = {
                        onItemClick(it)
                        onClose()
                    }
                )
            }
        }
    }
}

@Composable
fun DrawerHeader(onClose: () -> Unit) {
    val context = LocalContext.current
    val versionName = context.packageManager
        .getPackageInfo(context.packageName, 0).versionName
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { onClose() }
    ) {
        Image(
            painter = painterResource(id = R.mipmap.menu),
            contentDescription = "Background",
            modifier = Modifier
                .height(220.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )

    }
}


@Composable
fun DrawerBody(
    items: List<MenuItem>,
    onItemClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(0.8f)
        ) {
            items(items.size) { index ->
                val item = items[index]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClick(item.title)
                        }
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = bluecustom
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = item.title,
                        fontSize = 18.sp,
                        color = bluecustom
                    )
                }
            }
            item {
                Divider(color = PurpleGrey40)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClick("Salir")
                        }
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Salir",
                        tint = bluecustom
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Salir",
                        fontSize = 18.sp,
                        color = bluecustom
                    )
                }
            }
        }
    }
}

data class MenuItem(val title: String, val icon: ImageVector)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NavigationDrawerPreview() {
    NavigationDrawer(
        isVisible = true,
        onItemClick = {},
        onClose = {}
    )
}