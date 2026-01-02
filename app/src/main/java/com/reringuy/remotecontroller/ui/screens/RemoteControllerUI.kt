package com.reringuy.remotecontroller.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardReturn
import androidx.compose.material.icons.automirrored.rounded.VolumeOff
import androidx.compose.material.icons.automirrored.rounded.VolumeUp
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.DensityMedium
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Output
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material.icons.rounded.Tv
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.reringuy.remotecontroller.ui.components.GenericColumnButton
import com.reringuy.remotecontroller.ui.components.RemoteControllerSelectButton
import com.reringuy.remotecontroller.ui.theme.RemoteControllerTheme
import com.reringuy.remotecontroller.utils.IRCommand
import kotlin.math.atan2
import kotlin.math.roundToInt

val commonColor = Color.Gray

@Composable
fun RemoteControllerUI(
    onTriggerPower: () -> Unit,
    onMove: (IRCommand) -> Unit,
    onSelect: () -> Unit,
    onVolumeUp: () -> Unit,
    onVolumeDown: () -> Unit,
    onMute: () -> Unit,
    onChannelPlus: () -> Unit,
    onChannelMinus: () -> Unit,
    onReturn: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
            .padding(16.dp)
    ) {
        val (powerButton, selectButton, volume, mute, channel, options, returnButton) = createRefs()


        RemoteControllerPowerButton(
            modifier = Modifier.constrainAs(powerButton) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            onClick = onTriggerPower
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
            onMove = onMove,
            onSelect = onSelect
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
            onPlus = onVolumeUp,
            onMinus = onVolumeDown
        )

        Button(
            modifier = Modifier.constrainAs(mute) {
                top.linkTo(volume.bottom, margin = 8.dp)
                start.linkTo(volume.start)
                end.linkTo(volume.end)
            },
            onClick = onMute,
            colors = ButtonDefaults.buttonColors(
                containerColor = commonColor,
                contentColor = Color.Black
            )
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
            onPlus = onChannelPlus,
            onMinus = onChannelMinus
        )

        Button(
            modifier = Modifier.constrainAs(returnButton) {
                top.linkTo(channel.bottom, margin = 8.dp)
                end.linkTo(parent.end)
                start.linkTo(channel.start)
            },
            onClick = onReturn,
            colors = ButtonDefaults.buttonColors(
                containerColor = commonColor,
                contentColor = Color.Black
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardReturn,
                contentDescription = "Return"
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
        RemoteControllerOption(icon = Icons.Rounded.Output, onClick = { })
        RemoteControllerOption(icon = Icons.Rounded.DensityMedium, onClick = { })
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
        RemoteControllerUI(
            onTriggerPower = {  },
            onMove = {  },
            onSelect = {  },
            onVolumeUp = {  },
            onVolumeDown = {  },
            onMute = {  },
            onChannelPlus = {  },
            onChannelMinus = {  },
            onReturn = {  }
        )
    }
}

fun calculateNewAngle(mCenter: Offset, dragOffset: Offset): Float {
    val rad = atan2((mCenter.y - dragOffset.y).toDouble(), (mCenter.x - dragOffset.x).toDouble())
    val angle = Math.toDegrees(rad)
    return (angle + 180f).roundToInt().toFloat()
}