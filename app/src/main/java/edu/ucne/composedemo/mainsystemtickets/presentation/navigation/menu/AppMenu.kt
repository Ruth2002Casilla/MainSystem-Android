package edu.ucne.composedemo.mainsystemtickets.presentation.navigation.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import edu.ucne.composedemo.mainsystemtickets.R

@Composable
fun AppMenu(expandedState: MutableState<Boolean>, iconTintColor: Color = Color.White) {
    val expanded = expandedState.value
    val setExpanded = { value: Boolean -> expandedState.value = value }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = 55.dp)
            .wrapContentSize(Alignment.TopStart)
    ) {
        IconButton(onClick = { setExpanded(true) }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_menu_open_24),
                contentDescription = "Open Menu",
                tint = iconTintColor,
                modifier = Modifier.size(32.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { setExpanded(false) }
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(id = R.string.menu_home)) },
                onClick = { /* Acción de editar */ },
                leadingIcon = { Icon(Icons.Outlined.Home, contentDescription = null) }
            )
            HorizontalDivider()
            DropdownMenuItem(
                text = { Text(stringResource(id = R.string.menu_prioridades)) },
                onClick = { /* Acción de ajustes */ },
                leadingIcon = { Icon(Icons.Outlined.Favorite, contentDescription = null) }
            )
            DropdownMenuItem(
                text = { Text(stringResource(id = R.string.menu_sistema)) },
                onClick = { /* Acción de ajustes */ },
                leadingIcon = { Icon(Icons.Outlined.Build, contentDescription = null) }
            )
            DropdownMenuItem(
                text = { Text(stringResource(id = R.string.menu_cliente)) },
                onClick = { /* Acción de ajustes */ },
                leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = null) }
            )
            DropdownMenuItem(
                text = { Text(stringResource(id = R.string.menu_ticket)) },
                onClick = { /* Acción de ajustes */ },
                leadingIcon = { Icon(Icons.Outlined.CheckCircle, contentDescription = null) }
            )
            HorizontalDivider()
            DropdownMenuItem(
                text = { Text(stringResource(id = R.string.menu_info)) },
                onClick = { /* Acción de feedback */ },
                leadingIcon = { Icon(Icons.Outlined.Info, contentDescription = null) },
                trailingIcon = { Text("F11", textAlign = TextAlign.Center) }
            )
        }
    }
}
