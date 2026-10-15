package com.aistudio.aulaudio.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aistudio.aulaudio.theme.CarbonBlack
import com.aistudio.aulaudio.theme.TextSecondary
import com.aistudio.aulaudio.theme.White

enum class AppTab {
    AUDIO,
    MATERIAS,
    GUARDADOS,
    AJUSTES
}

@Composable
fun BottomNavBar(
    selectedTab: AppTab,
    onTabSelected: (AppTab) -> Unit,
    onRecordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0x00FAFAFA),
                        Color(0xE6FAFAFA),
                        Color(0xFFFAFAFA)
                    )
                )
            )
            .navigationBarsPadding()
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tab: Audio
            NavTabItem(
                label = "Audio",
                selected = selectedTab == AppTab.AUDIO,
                icon = { Icon(AulaIcons.GraphicEq, contentDescription = "Audio", modifier = Modifier.size(22.dp)) },
                onClick = { onTabSelected(AppTab.AUDIO) },
                testTag = "nav_tab_audio"
            )

            // Tab: Materias
            NavTabItem(
                label = "Materias",
                selected = selectedTab == AppTab.MATERIAS,
                icon = { Icon(AulaIcons.Folder, contentDescription = "Materias", modifier = Modifier.size(22.dp)) },
                onClick = { onTabSelected(AppTab.MATERIAS) },
                testTag = "nav_tab_materias"
            )

            // Center Record Action Button
            Box(
                modifier = Modifier
                    .offset(y = (-8).dp)
                    .size(54.dp)
                    .shadow(elevation = 6.dp, shape = CircleShape)
                    .clip(CircleShape)
                    .background(CarbonBlack)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onRecordClick
                    )
                    .testTag("nav_record_button"),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = AulaIcons.Mic,
                    contentDescription = "Iniciar Grabación",
                    tint = White,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Tab: Guardados
            NavTabItem(
                label = "Guardados",
                selected = selectedTab == AppTab.GUARDADOS,
                icon = {
                    Icon(
                        if (selectedTab == AppTab.GUARDADOS) AulaIcons.Bookmark else AulaIcons.BookmarkBorder,
                        contentDescription = "Guardados",
                        modifier = Modifier.size(22.dp)
                    )
                },
                onClick = { onTabSelected(AppTab.GUARDADOS) },
                testTag = "nav_tab_guardados"
            )

            // Tab: Ajustes
            NavTabItem(
                label = "Ajustes",
                selected = selectedTab == AppTab.AJUSTES,
                icon = { Icon(AulaIcons.Tune, contentDescription = "Ajustes", modifier = Modifier.size(22.dp)) },
                onClick = { onTabSelected(AppTab.AJUSTES) },
                testTag = "nav_tab_ajustes"
            )
        }
    }
}

@Composable
private fun NavTabItem(
    label: String,
    selected: Boolean,
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
    testTag: String
) {
    val color = if (selected) CarbonBlack else TextSecondary
    Column(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .testTag(testTag),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            icon()
        }
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            color = color,
            letterSpacing = (-0.01).sp
        )
    }
}
