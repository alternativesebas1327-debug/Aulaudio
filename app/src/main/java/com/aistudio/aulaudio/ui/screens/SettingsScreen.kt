package com.aistudio.aulaudio.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aistudio.aulaudio.theme.CarbonBlack
import com.aistudio.aulaudio.theme.SurfaceBorderSubtle
import com.aistudio.aulaudio.theme.SurfaceCanvas
import com.aistudio.aulaudio.theme.SurfaceContainer
import com.aistudio.aulaudio.theme.TextPrimary
import com.aistudio.aulaudio.theme.TextSecondary
import com.aistudio.aulaudio.theme.TextTertiary
import com.aistudio.aulaudio.theme.White
import com.aistudio.aulaudio.ui.components.AulaIcons

@Composable
fun SettingsScreen(
    totalRecordingsCount: Int,
    modifier: Modifier = Modifier
) {
    var isNoiseReductionEnabled by remember { mutableStateOf(true) }
    var isAutoSummaryEnabled by remember { mutableStateOf(true) }
    var isHapticEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceCanvas)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceCanvas)
                .statusBarsPadding()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Column {
                Text(
                    text = "PREFERENCIAS",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextTertiary,
                    letterSpacing = 1.5.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Ajustes",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary,
                    letterSpacing = (-0.03).sp
                )
            }
        }

        HorizontalDivider(color = SurfaceBorderSubtle, thickness = 1.dp)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp)
        ) {
            item {
                SectionHeader("AUDIO Y GRABACIÓN")
                SettingToggleRow(
                    icon = AulaIcons.Mic,
                    title = "Reducción de ruido ambiental",
                    subtitle = "Optimiza la nitidez de la voz del profesor",
                    checked = isNoiseReductionEnabled,
                    onCheckedChange = { isNoiseReductionEnabled = it }
                )
                SettingToggleRow(
                    icon = AulaIcons.GraphicEq,
                    title = "Procesamiento de audio limpio",
                    subtitle = "Ecualización equilibrada para audición prolongada",
                    checked = isAutoSummaryEnabled,
                    onCheckedChange = { isAutoSummaryEnabled = it }
                )
                SettingToggleRow(
                    icon = AulaIcons.Tune,
                    title = "Respuesta háptica",
                    subtitle = "Vibración sutil al fijar marcadores y pausar",
                    checked = isHapticEnabled,
                    onCheckedChange = { isHapticEnabled = it }
                )

                Spacer(modifier = Modifier.height(24.dp))
                SectionHeader("ALMACENAMIENTO Y CLASES")

                SettingInfoRow(
                    icon = AulaIcons.Folder,
                    title = "Sesiones almacenadas",
                    value = "$totalRecordingsCount grabaciones"
                )
                SettingInfoRow(
                    icon = AulaIcons.GraphicEq,
                    title = "Formato de audio",
                    value = "MPEG-4 AAC (128 kbps)"
                )

                Spacer(modifier = Modifier.height(24.dp))
                SectionHeader("SISTEMA DE DISEÑO")

                SettingInfoRow(
                    icon = Icons.Filled.Info,
                    title = "Estética de interfaz",
                    value = "Nordic Precision"
                )
                SettingInfoRow(
                    icon = Icons.Filled.Info,
                    title = "Versión de AulaAudio",
                    value = "1.0.0"
                )

                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,
        color = TextTertiary,
        letterSpacing = 1.2.sp,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
private fun SettingToggleRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f).padding(end = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(SurfaceContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = CarbonBlack,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextPrimary
                )
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = TextTertiary
                )
            }
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = White,
                checkedTrackColor = CarbonBlack,
                uncheckedThumbColor = White,
                uncheckedTrackColor = SurfaceContainer
            )
        )
    }
    HorizontalDivider(color = SurfaceBorderSubtle, thickness = 0.5.dp)
}

@Composable
private fun SettingInfoRow(
    icon: ImageVector,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(SurfaceContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = CarbonBlack,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = TextPrimary
            )
        }
        Text(
            text = value,
            fontSize = 13.sp,
            color = TextSecondary
        )
    }
    HorizontalDivider(color = SurfaceBorderSubtle, thickness = 0.5.dp)
}
