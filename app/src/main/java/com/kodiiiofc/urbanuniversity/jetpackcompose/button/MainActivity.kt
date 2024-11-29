package com.kodiiiofc.urbanuniversity.jetpackcompose.button

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kodiiiofc.urbanuniversity.jetpackcompose.button.ui.theme.ButtonTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomButtonPreview()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RandomButtonPreview() {

    val changeParams = {
        Triple(
            Color.hsl(
                hue = Random.nextFloat() * 360,
                saturation = 0.3f,
                lightness = 0.5f
            ),
            Color.hsl(
                hue = Random.nextFloat() * 360,
                saturation = 0.7f,
                lightness = 0.5f
            ),
            (0..32).random().dp
        )
    }

    val firstButtonSettings = rememberSaveable {
        mutableStateOf(
            changeParams()
        )
    }
    val secondButtonSettings = rememberSaveable {
        mutableStateOf(
            changeParams()
        )
    }
    val thirdButtonSettings = rememberSaveable {
        mutableStateOf(
            changeParams()
        )
    }



    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RandomButton(
            text = "First",
            buttonColor = firstButtonSettings.value.first,
            borderColor = firstButtonSettings.value.second,
            borderThickness = firstButtonSettings.value.third,
            onClick = {
                secondButtonSettings.value = changeParams()
                thirdButtonSettings.value = changeParams()
            }
        )
        RandomButton(
            text = "Second",
            buttonColor = secondButtonSettings.value.first,
            borderColor = secondButtonSettings.value.second,
            borderThickness = secondButtonSettings.value.third,
            onClick = {
                firstButtonSettings.value = changeParams()
                thirdButtonSettings.value = changeParams()
            }
        )
        RandomButton(
            text = "Third",
            buttonColor = thirdButtonSettings.value.first,
            borderColor = thirdButtonSettings.value.second,
            borderThickness = thirdButtonSettings.value.third,
            onClick = {
                firstButtonSettings.value = changeParams()
                secondButtonSettings.value = changeParams()
            }
        )
    }


}

@Composable
fun RandomButton(
    text: String,
    buttonColor: Color,
    borderColor: Color,
    borderThickness: Dp,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        colors = ButtonColors(
            containerColor = buttonColor,
            contentColor = Color.White,
            disabledContentColor = Color.LightGray,
            disabledContainerColor = Color.DarkGray
        ),
        border = BorderStroke(
            borderThickness,
            borderColor
        ),
        contentPadding = PaddingValues(64.dp, 32.dp)
    ) { Text(text) }
}