package ca.gbc.comp3074.fahim_mina.labex2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.gbc.comp3074.fahim_mina.labex2.ui.theme.LabEx2Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            LabEx2Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    ActionButtons(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ActionButtons(modifier: Modifier) {

    val count = remember {
        mutableStateOf(0)
    }

    val step = remember {
        mutableStateOf(1)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.light_pink_background)
            )
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = count.value.toString(),
            fontSize = 32.sp
        )

        Spacer(
            modifier = Modifier.height(40.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(40.dp)
        ) {

            Button(
                onClick = {
                    count.value = count.value - step.value
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.green_button)
                )
            ) {
                Text("-")
            }

            Button(
                onClick = {
                    count.value = count.value + step.value
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.green_button)
                )
            ) {
                Text("+")
            }
        }

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(40.dp)
        ) {

            Button(
                onClick = {
                    count.value = 0
                    step.value = 1
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.pink_reset)
                )
            ) {
                Text("Reset")
            }

            Button(
                onClick = {
                    step.value = 2
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.green2_step)
                )
            ) {
                Text("Step")
            }
        }
    }
}