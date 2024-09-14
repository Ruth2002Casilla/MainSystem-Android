package edu.ucne.composedemo.mainsystemtickets.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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


@Composable
fun ScreenIntro() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.mipmap.ico_intro),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(id = R.string.bienvenido),
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .offset(y = 510.dp)
        )
        Text(
            text = stringResource(id = R.string.descripcion),
            fontSize = 13.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .offset(y = 590.dp)
        )
        Button(
            onClick = { /*Algo*/ },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.custom_red)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(126.dp)
                .offset(y = 550.dp)
                .wrapContentWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = stringResource(id = R.string.start),
                fontSize = 15.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_forward_24), // Make sure to replace with actual drawable resource
                contentDescription = "Arrow Right",
                tint = Color.White,
                modifier = Modifier.padding(end = 8.dp)
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ScreenIntroPreview() {
    ScreenIntro()
}