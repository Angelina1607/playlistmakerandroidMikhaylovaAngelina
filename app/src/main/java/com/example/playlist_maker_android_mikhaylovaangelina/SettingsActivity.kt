package com.example.playlist_maker_android_mikhaylovaangelina

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playlist_maker_android_mikhaylovaangelina.ui.theme.PlaylistmakerandroidMikhaylovaAngelinaTheme

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaylistmakerandroidMikhaylovaAngelinaTheme {
                SettingsScreen(
                    onBackClick = { finish() }
                )
            }
        }
    }
}

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current
    var isDarkTheme by remember { mutableStateOf(false) }

    // Получаем строки из strings.xml через stringResource()
    val shareMessage = stringResource(id = R.string.share_message)
    val supportEmail = stringResource(id = R.string.support_email)
    val supportSubject = stringResource(id = R.string.support_subject)
    val supportBody = stringResource(id = R.string.support_body)
    val agreementUrl = stringResource(id = R.string.user_agreement_url)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .padding(horizontal = 16.dp)
    ) {
        // Верхняя панель со стрелкой назад и заголовком "Настройки"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "←",
                fontSize = 24.sp,
                color = Color(0xFF1A1B22),
                modifier = Modifier
                    .clickable { onBackClick() }
                    .padding(end = 8.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(id = R.string.settings_title),
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF1A1B22)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Пункт 1: Темная тема (переключатель по макету)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(61.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.dark_theme),
                fontSize = 16.sp,
                color = Color(0xFF1A1B22)
            )
            Switch(
                checked = isDarkTheme,
                onCheckedChange = { isDarkTheme = it },
                colors = SwitchDefaults.colors(
                    uncheckedThumbColor = Color(0xFFAEAFB4),
                    uncheckedTrackColor = Color(0xFFE6E8EB),
                    uncheckedBorderColor = Color.Transparent,
                    checkedThumbColor = Color(0xFF3772E7),
                    checkedTrackColor = Color(0xFF9FBBF3)
                )
            )
        }

        // Пункт 2: Поделиться приложением
        SettingsItem(
            title = stringResource(id = R.string.share_app),
            iconRes = android.R.drawable.ic_menu_share,
            onClick = {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, shareMessage)
                }
                context.startActivity(Intent.createChooser(shareIntent, null))
            }
        )

        // Пункт 3: Написать в поддержку
        SettingsItem(
            title = stringResource(id = R.string.write_to_support),
            iconRes = android.R.drawable.ic_dialog_email,
            onClick = {
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(supportEmail))
                    putExtra(Intent.EXTRA_SUBJECT, supportSubject)
                    putExtra(Intent.EXTRA_TEXT, supportBody)
                }
                context.startActivity(emailIntent)
            }
        )

        // Пункт 4: Пользовательское соглашение
        SettingsItem(
            title = stringResource(id = R.string.user_agreement),
            trailingText = "›",
            onClick = {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(agreementUrl))
                context.startActivity(browserIntent)
            }
        )
    }
}

@Composable
private fun SettingsItem(
    title: String,
    iconRes: Int? = null,
    trailingText: String? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(61.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color(0xFF1A1B22)
        )
        if (iconRes != null) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                tint = Color(0xFFAEAFB4),
                modifier = Modifier.size(20.dp)
            )
        } else if (trailingText != null) {
            Text(
                text = trailingText,
                fontSize = 24.sp,
                color = Color(0xFFAEAFB4)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    PlaylistmakerandroidMikhaylovaAngelinaTheme {
        SettingsScreen()
    }
}
