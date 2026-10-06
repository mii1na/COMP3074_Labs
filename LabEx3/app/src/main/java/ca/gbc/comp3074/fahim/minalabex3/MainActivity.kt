package ca.gbc.comp3074.fahim.minalabex3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.gbc.comp3074.fahim.minalabex3.ui.theme.Labex3Theme

data class Message(
    val name: String,
    val message: String
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Labex3Theme {
                MessageScreen()
            }
        }
    }
}

@Composable
fun MessageScreen() {

    val messages = listOf(
        Message("Joe", "Hi!"),
        Message("Jim", "How are you?"),
        Message("Joe", "Test..1..2...3"),
        Message("Joe", "I hate coding!!!"),

        Message("Joe", "Hi!"),
        Message("Jim", "How are you?"),
        Message("Joe", "Test..1..2...3"),
        Message("Joe", "I hate coding!!!"),

        Message("Joe", "Hi!"),
        Message("Jim", "How are you?"),
        Message("Joe", "Test..1..2...3"),
        Message("Joe", "I hate coding!!!")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF7FF))
            .padding(
                start = 28.dp,
                top = 12.dp,
                end = 16.dp
            )
    ) {

        items(messages) { item ->

            MessageItem(
                name = item.name,
                message = item.message
            )
        }
    }
}

@Composable
fun MessageItem(
    name: String,
    message: String
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 12.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier
                .size(50.dp)
                .border(
                    width = 1.5.dp,
                    color = Color.Red,
                    shape = CircleShape
                )
                .padding(10.dp)
        )

        Spacer(
            modifier = Modifier.size(10.dp)
        )

        Column {

            Text(
                text = name,
                fontSize = 16.sp,
                color = Color(0xFF55505A)
            )

            Spacer(
                modifier = Modifier.size(4.dp)
            )

            Text(
                text = message,
                fontSize = 16.sp,
                color = Color(0xFF3F3A40),
                modifier = Modifier
                    .background(
                        color = Color(0xFFF3EDF4),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(
                        horizontal = 7.dp,
                        vertical = 5.dp
                    )
            )
        }
    }
}