package com.reringuy.remotecontroller.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toOffset
import com.reringuy.remotecontroller.ui.screens.calculateNewAngle
import com.reringuy.remotecontroller.utils.IRCommand

@Composable
fun RemoteControllerSelectButton(
    modifier: Modifier,
    onMove: (IRCommand) -> Unit,
    onSelect: () -> Unit,
) {
    val textMeasurer = rememberTextMeasurer()
    val onSelectedColor = Color(0xff137508)
    var dragOffset by remember {
        mutableStateOf(Offset.Zero)
    }
    var newAngle by remember { mutableStateOf(Float.MAX_VALUE) }
    var okBtnColor by remember { mutableStateOf(false) }

    val animateUpColor by animateColorAsState(
        targetValue = if (newAngle in 225f..315f) onSelectedColor else Color.Black,
        animationSpec = tween(50),
        finishedListener = { newAngle = Float.MAX_VALUE }
    )
    val animateRightColor by animateColorAsState(
        targetValue = if (newAngle in 315f..360f || newAngle in 0f..45f) onSelectedColor else Color.Black,
        animationSpec = tween(50),
        finishedListener = { newAngle = Float.MAX_VALUE }
    )
    val animateDownColor by animateColorAsState(
        targetValue = if (newAngle in 45f..135f) onSelectedColor else Color.Black,
        animationSpec = tween(50),
        finishedListener = { newAngle = Float.MAX_VALUE }
    )
    val animateLeftColor by animateColorAsState(
        targetValue = if (newAngle in 135f..225f) onSelectedColor else Color.Black,
        animationSpec = tween(50),
        finishedListener = { newAngle = Float.MAX_VALUE }
    )
    val animateOkColor by animateColorAsState(
        targetValue = if (okBtnColor) onSelectedColor else Color.White,
        animationSpec = tween(50),
        finishedListener = { okBtnColor = false }
    )

    Canvas(
        modifier = modifier
            .size(200.dp)
            .aspectRatio(1f)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        dragOffset = it
                        val okBtnRadius = size.center.x / 2f

                        if (dragOffset.x in size.center.x - okBtnRadius..size.center.x + okBtnRadius
                            && dragOffset.y in size.center.y - okBtnRadius..size.center.y + okBtnRadius
                        ) {
                            okBtnColor = true
                            onSelect()
                            return@detectTapGestures
                        }

                        newAngle = calculateNewAngle(size.center.toOffset(), dragOffset)

                        when (newAngle) {
                            in 225f..315f -> {
                                onMove(IRCommand.UP)
                            }

                            in 315f..360f -> {
                                onMove(IRCommand.RIGHT)
                            }

                            in 0f..45f -> {
                                onMove(IRCommand.RIGHT)
                            }

                            in 45f..135f -> {
                                onMove(IRCommand.DOWN)
                            }

                            in 135f..225f -> {
                                onMove(IRCommand.LEFT)
                            }
                        }
                    }
                )
            }
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
            }
            .drawWithContent {

                val arrowSize = center.x / 10f
                val okText = textMeasurer.measure(
                    "OK", style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                drawArc(
                    color = animateUpColor,
                    startAngle = 225f,
                    sweepAngle = 90f,
                    useCenter = true,
                    size = Size(size.width, size.width),
                    topLeft = Offset(0f, 0f)
                )

                drawPath(
                    path = Path().apply {
                        reset()
                        moveTo(center.x - arrowSize, arrowSize * 2)
                        lineTo(center.x, arrowSize)
                        lineTo(center.x + arrowSize, arrowSize * 2)
                        close()
                    },
                    color = Color.White
                )

                rotate(90f, center) {
                    drawArc(
                        color = animateRightColor,
                        startAngle = 225f,
                        sweepAngle = 90f,
                        useCenter = true,
                        size = Size(size.width, size.width),
                        topLeft = Offset(0f, 0f)
                    )
                    drawPath(
                        path = Path().apply {
                            reset()
                            moveTo(center.x - arrowSize, arrowSize * 2)
                            lineTo(center.x, arrowSize)
                            lineTo(center.x + arrowSize, arrowSize * 2)
                            close()
                        },
                        color = Color.White
                    )
                }

                rotate(180f, center) {
                    drawArc(
                        color = animateDownColor,
                        startAngle = 225f,
                        sweepAngle = 90f,
                        useCenter = true,
                        size = Size(size.width, size.width),
                        topLeft = Offset(0f, 0f)
                    )
                    drawPath(
                        path = Path().apply {
                            reset()
                            moveTo(center.x - arrowSize, arrowSize * 2)
                            lineTo(center.x, arrowSize)
                            lineTo(center.x + arrowSize, arrowSize * 2)
                            close()
                        },
                        color = Color.White
                    )
                }

                rotate(270f, center) {
                    drawArc(
                        color = animateLeftColor,
                        startAngle = 225f,
                        sweepAngle = 90f,
                        useCenter = true,
                        size = Size(size.width, size.width),
                        topLeft = Offset(0f, 0f)
                    )
                    drawPath(
                        path = Path().apply {
                            reset()
                            moveTo(center.x - arrowSize, arrowSize * 2)
                            lineTo(center.x, arrowSize)
                            lineTo(center.x + arrowSize, arrowSize * 2)
                            close()
                        },
                        color = Color.White
                    )
                }

                drawCircle(Color.Gray, radius = center.x / 2f)


                drawText(
                    textMeasurer = textMeasurer,
                    text = "OK",
                    topLeft = Offset(
                        center.x - okText.size.width / 2f,
                        center.y - okText.size.height / 2f
                    ),
                    style = TextStyle(
                        color = animateOkColor,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )
            }) {
    }
}