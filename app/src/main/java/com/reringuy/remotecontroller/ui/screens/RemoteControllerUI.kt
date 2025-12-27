package com.reringuy.remotecontroller.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.VolumeOff
import androidx.compose.material.icons.automirrored.rounded.VolumeUp
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.DensityMedium
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Numbers
import androidx.compose.material.icons.rounded.Output
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material.icons.rounded.Tv
import androidx.compose.material.icons.rounded.VolumeOff
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toOffset
import androidx.constraintlayout.compose.ConstraintLayout
import com.reringuy.remotecontroller.ui.components.GenericColumnButton
import com.reringuy.remotecontroller.ui.components.RemoteControllerSelectButton
import com.reringuy.remotecontroller.ui.theme.RemoteControllerTheme
import com.reringuy.remotecontroller.utils.Direction
import kotlin.io.path.Path
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.hypot
import kotlin.math.roundToInt

val commonColor = Color.Gray

@Composable
fun RemoteControllerUI() {
    Surface(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
        {
            val (powerButton, selectButton, volume, mute, channel, options) = createRefs()


            RemoteControllerPowerButton(
                modifier = Modifier.constrainAs(powerButton) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
                onClick = { TODO() }
            )

            RemoteControllerOptionsRow(
                modifier = Modifier.constrainAs(options) {
                    bottom.linkTo(selectButton.top, margin = 48.dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
            )

            RemoteControllerSelectButton(
                modifier = Modifier.constrainAs(selectButton) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
                onMove = { TODO() },
                onSelect = { TODO() }
            )

            GenericColumnButton(
                modifier = Modifier.constrainAs(volume) {
                    end.linkTo(selectButton.start)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
                icon = Icons.AutoMirrored.Rounded.VolumeUp,
                plusIcon = Icons.Rounded.Add,
                minusIcon = Icons.Rounded.Remove,
                onPlus = { TODO() },
                onMinus = { TODO() }
            )

            Button(
                modifier = Modifier.constrainAs(mute) {
                    top.linkTo(volume.bottom, margin = 8.dp)
                    start.linkTo(volume.start)
                    end.linkTo(volume.end)
                },
                onClick = { TODO() },
                colors = ButtonDefaults.buttonColors(containerColor = commonColor, contentColor = Color.Black)
            ) {
                Icon(imageVector = Icons.AutoMirrored.Rounded.VolumeOff, contentDescription = "Mute")
            }

            GenericColumnButton(
                modifier = Modifier.constrainAs(channel) {
                    start.linkTo(selectButton.end)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
                icon = Icons.Rounded.Tv,
                plusIcon = Icons.Rounded.KeyboardArrowUp,
                minusIcon = Icons.Rounded.KeyboardArrowDown,
                onPlus = { TODO() },
                onMinus = { TODO() }
            )

        }
    }
}

@Composable
fun RemoteControllerOptionsRow(modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        RemoteControllerOption(icon = Icons.Rounded.Output, onClick = { TODO() })
        RemoteControllerOption(icon = Icons.Rounded.DensityMedium, onClick = { TODO() })
    }
}

@Composable
fun RemoteControllerOption(icon: ImageVector, onClick: () -> Unit) {
    Button(onClick = onClick, colors = ButtonDefaults.buttonColors(containerColor = commonColor)) {
        Icon(imageVector = icon, contentDescription = "Option")
    }
}

@Composable
fun RemoteControllerPowerButton(modifier: Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier.size(64.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = commonColor,
            contentColor = Color(0xff8f0b19)
        ),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(imageVector = Icons.Default.PowerSettingsNew, contentDescription = "Power")
    }
}

@Preview
@Composable
fun RemoteControllerUIPreview() {
    RemoteControllerTheme {
        RemoteControllerUI()
    }
}

fun calculateNewAngle(mCenter: Offset, dragOffset: Offset): Float {
    val rad = atan2((mCenter.y - dragOffset.y).toDouble(), (mCenter.x - dragOffset.x).toDouble())
    val angle = Math.toDegrees(rad)
    return (angle + 180f).roundToInt().toFloat()
}