package com.example.playlist_maker_android_mikhaylovaangelina

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.annotation.DrawableRes
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playlist_maker_android_mikhaylovaangelina.ui.theme.PlaylistmakerandroidMikhaylovaAngelinaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaylistmakerandroidMikhaylovaAngelinaTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize().background(Color(55, 114, 231))) {
        // Шапка
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(55, 114, 231))
                .statusBarsPadding()
                .height(74.dp)
        ) {
            Text(
                text = "Playlist Maker",
                style = TextStyle(
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
            )
        }

        // Белая область со скруглёнными верхними углами, наезжает на шапку
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 0.dp)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(Color.White)
        ) {
            MenuItem(
                icon = R.drawable.search,
                title = "Поиск",
                onClick = { context.startActivity(Intent(context, SearchActivity::class.java)) }
            )
            MenuItem(
                icon = R.drawable.library,
                title = "Плейлисты",
                onClick = { /* экран пока не нужен */ }
            )
            MenuItem(
                icon = R.drawable.favorite_border,
                title = "Избранное",
                onClick = { /* экран пока не нужен */ }
            )
            MenuItem(
                icon = R.drawable.favorite_border,
                title = "Настройки",
                onClick = { context.startActivity(Intent(context, SettingsActivity::class.java)) }
            )
        }
    }
}

@Composable
fun MenuItem(@DrawableRes icon: Int, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 30.dp, vertical = 22.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Color(0xFF1A1B22)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF1A1B22)
            ),
            modifier = Modifier.weight(1f)
        )
        Icon(
            painter = painterResource(R.drawable.arrow_forward),
            contentDescription = null,
            tint = Color(0xFFAEAFB4)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    PlaylistmakerandroidMikhaylovaAngelinaTheme {
        MainScreen()
    }
}