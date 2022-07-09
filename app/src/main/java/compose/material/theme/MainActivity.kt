package compose.material.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import compose.material.theme.ui.theme.Material3ComposeTheme
import kotlinx.coroutines.delay

/*
*
*  How to create swipe to refresh in Jetpack compose
*  https://www.boltuix.com/2022/07/how-to-create-swipe-to-refresh-in.html
*
*  List view & Divider
*  https://www.boltuix.com/2021/12/lazy-column_29.html
* */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Material3ComposeTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                 SwipeRefreshCompose()
                }
            }
        }
    }
}

@Composable
fun SwipeRefreshCompose() {

    var refreshing by remember { mutableStateOf(false) }

    LaunchedEffect(refreshing) {
        if (refreshing) {
            delay(3000)
            refreshing = false
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = { refreshing = true },
    ) {

        Column {
            Text(
                text = "Swipe to refresh UX",
                color = Color.Black,
                fontSize = 22.sp,
                letterSpacing = 2.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
                    .padding(20.dp)
            )

            Spacer(modifier = Modifier.padding(2.dp))

            LazyColumn {

                    items(count = 10) {

                            Column(
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.identified),
                                        contentDescription = "Profile Image",
                                        contentScale = ContentScale.FillBounds,
                                        modifier = Modifier
                                            .size(70.dp)
                                            .clip(CircleShape)
                                    )

                                    Spacer(modifier = Modifier.padding(5.dp))

                                    Column {
                                        Text(
                                            text = "Bolt UIX",
                                            color = Color.Black,
                                            fontWeight = FontWeight.Bold,
                                            style = MaterialTheme.typography.headlineSmall,
                                            letterSpacing = 2.sp
                                        )

                                        Spacer(modifier = Modifier.padding(2.dp))

                                        Text(
                                            text = "Get started with Beautiful UI UX design patterns.",
                                            color = Color.Gray,
                                            style = MaterialTheme.typography.bodyLarge,
                                            letterSpacing = 1.sp
                                        )
                                    }
                                }
                            }

                             ListDivider()


                    }
            }


        }

    }

}



/**
 * Full-width divider with padding
 */
@Composable
fun ListDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
    )
}