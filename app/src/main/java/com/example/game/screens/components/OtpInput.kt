package com.example.game.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight

@Composable
fun OtpInput(
    otp: String,
    onOtpChange: (String) -> Unit,
    length: Int = 4,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme

    BasicTextField(
        value = otp,
        onValueChange = { raw ->
            val filtered = raw.filter { it.isDigit() }.take(length)
            onOtpChange(filtered)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        cursorBrush = SolidColor(colorScheme.primary),
        textStyle = TextStyle(
            color = colorScheme.onPrimary,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                repeat(length) { index ->
                    val char = otp.getOrNull(index)?.toString() ?: ""
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(50.dp)
                            .height(60.dp)
                    ) {

                        Canvas(
                            modifier = Modifier
                                .matchParentSize()
                        ) {
                            val lineColor = if (char.isEmpty())
                                colorScheme.onSurface.copy(alpha = 0.4f)
                            else colorScheme.primary

                            drawLine(
                                color = lineColor,
                                start = Offset(0f, size.height - 5),
                                end = Offset(size.width, size.height - 5),
                                strokeWidth = 3f
                            )
                        }

                        Text(
                            text = char,
                            fontSize = 22.sp,
                            color = colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    )
}