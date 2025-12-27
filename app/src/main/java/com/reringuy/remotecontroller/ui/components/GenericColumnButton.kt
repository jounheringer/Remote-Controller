package com.reringuy.remotecontroller.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun GenericColumnButton(
    modifier: Modifier,
    icon: ImageVector,
    plusIcon: ImageVector,
    minusIcon: ImageVector,
    onPlus: () -> Unit,
    onMinus: () -> Unit,
) {
    val iconSize = 64.dp
    Column(
        modifier = modifier.background(Color.Gray, RoundedCornerShape(24.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        IconButton(modifier = Modifier.size(iconSize), onClick = onPlus) {
            Icon(
                modifier = Modifier.size(iconSize / 1.5f),
                imageVector = plusIcon,
                contentDescription = "Plus"
            )
        }

        Icon(
            modifier = Modifier.size(iconSize / 2f),
            imageVector = icon,
            contentDescription = "Icon"
        )

        IconButton(modifier = Modifier.size(iconSize), onClick = onMinus) {
            Icon(
                modifier = Modifier.size(iconSize / 1.5f),
                imageVector = minusIcon,
                contentDescription = "Plus"
            )
        }
    }
}