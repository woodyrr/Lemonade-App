package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeAppTheme {
                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    Lemonade()
                }
            }
        }
    }
}

@Composable
fun Lemonade(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableIntStateOf(1) }
    val squeezeCounts = (2..4).random()
    var squeezeCount by remember { mutableIntStateOf(squeezeCounts) }

    val imageResource = when (currentStep) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }
    val description = when (currentStep) {
        1 -> R.string.step_1
        2 -> R.string.step_2
        3 -> R.string.step_3
        4 -> R.string.step_4
        else -> R.string.step_1
    }
    Column (modifier = Modifier.fillMaxSize(),  horizontalAlignment = Alignment.End){
        Row (modifier = Modifier
            .height(95.dp)
            .background(Color.Yellow), verticalAlignment = Alignment.Bottom){
            Text(text = stringResource(id = R.string.lemonade),
                modifier = Modifier

                    .fillMaxWidth()

                    .padding(bottom = 18.dp)
                    ,

                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp

                )
        }
        Column (modifier = modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Button(
                onClick = {when (currentStep){
                    1 -> {currentStep++
                        squeezeCount = squeezeCounts
                    }
                    2 -> {
                        squeezeCount--
                        when (squeezeCount) {0 -> currentStep++}
                    }
                    3 -> currentStep++
                    4 -> currentStep = 1
                }
                          },

                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .padding(20.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(155, 235, 206)), shape = RoundedCornerShape(40.dp) ) {
                Image(painter = painterResource(imageResource) ,
                    contentDescription = currentStep.toString(),
                )

            }
            Spacer(modifier = Modifier.padding(2.dp))

            Text(text = stringResource(description), modifier = Modifier, fontSize = 18.sp)

        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeAppTheme {
        Lemonade()
    }
}