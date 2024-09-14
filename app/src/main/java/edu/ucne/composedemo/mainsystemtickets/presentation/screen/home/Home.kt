package edu.ucne.composedemo.mainsystemtickets.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.ucne.composedemo.mainsystemtickets.R
import edu.ucne.composedemo.mainsystemtickets.presentation.component.NavigationDrawer

@Composable
fun ScreenHome() {
    val expandedState = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.mipmap.ico_home),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(id = R.string.system),
            fontSize = 15.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 151.dp)
                .offset(y = 20.dp)
        )

        NavigationDrawer(expandedState)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = 53.dp)
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = { /**/ }) {
            Icon(
                painter = painterResource(id = R.drawable.sharp_supervised_user_circle_24),
                contentDescription = "User",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ScreenHomePreview() {
    ScreenHome()
}
