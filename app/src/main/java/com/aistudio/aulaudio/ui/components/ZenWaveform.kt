package com.aistudio.aulaudio.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aistudio.aulaudio.theme.CarbonBlack
import com.aistudio.aulaudio.theme.SurfaceBorder
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun ZenWaveformRecording(
    amplitude: Float,
    isPaused: Boolean,
    modifier: Modifier = Modifier,
    barCount: Int = 36
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until barCount) {
            val progress = i.toFloat() / barCount
            val curve = sin(progress * PI).toFloat()
            // Dynamic oscillation per bar
            val barFactor = if (isPaused) 0.15f else (0.2f + (sin((i * 1.3f) + (amplitude * 10f)) * 0.4f + 0.5f) * amplitude)
            val animatedHeightFraction by animateFloatAsState(
                targetValue = (curve * barFactor).coerceIn(0.1f, 0.95f),
                animationSpec = tween(durationMillis = 140),
                label = "bar_$i"
            )

            val isCenter = i in (barCount / 4)..(barCount * 3 / 4)
            val barColor = if (isPaused) {
                SurfaceBorder
            } else if (isCenter) {
                CarbonBlack
            } else {
                Color(0xFF71717A)
            }

            Box(
                modifier = Modifier
                    .width(3.dp)
                    .fillMaxHeight(animatedHeightFraction)
                    .clip(RoundedCornerShape(999.dp))
                    .background(barColor)
            )
        }
    }
}

@Composable
fun ZenWaveformPlayer(
    currentProgress: Float,
    onSeek: (Float) -> Unit,
    modifier: Modifier = Modifier,
    barCount: Int = 30
) {
    // Preset dynamic wave heights to emulate lecture speech pattern
    val heights = remember {
        listOf(
            0.18f, 0.32f, 0.55f, 0.82f, 0.45f, 0.92f, 0.98f, 0.65f, 0.88f, 0.75f,
            0.40f, 0.68f, 0.95f, 0.52f, 0.85f, 0.28f, 0.48f, 0.72f, 0.96f, 0.60f,
            0.82f, 0.38f, 0.64f, 0.78f, 0.35f, 0.55f, 0.22f, 0.42f, 0.30f, 0.15f
        )
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until barCount) {
            val barFraction = i.toFloat() / (barCount - 1)
            val isPlayed = barFraction <= currentProgress
            val heightFrac = heights.getOrElse(i) { 0.4f }

            val barColor = if (isPlayed) CarbonBlack else SurfaceBorder

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(heightFrac)
                    .clip(RoundedCornerShape(999.dp))
                    .background(barColor)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onSeek(barFraction)
                    }
            )
        }
    }
}
