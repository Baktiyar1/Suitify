package com.example.suitify.ui.dialog

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.DialogProperties
import com.example.core_ui.R
import com.example.core_ui.theme.ButtonGradientEndColor
import com.example.core_ui.theme.ButtonGradientStartColor
import com.example.core_ui.theme.DialogBackgroundColor
import com.example.core_ui.theme.MainCardColor
import com.example.core_ui.theme.MainTextColor
import com.example.core_ui.theme.MusicTopMixItemBackgroundColor
import com.example.core_ui.theme.dp2
import com.example.core_ui.theme.dp21
import com.example.core_ui.theme.dp26
import com.example.core_ui.theme.dp32
import com.example.core_ui.theme.dp8

@Composable
fun PermissionDialog(
    context: Context,
    permissionTextProvides: PermissionTextProvides,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            GradientButton(text = stringResource(
                id = if (isPermanentlyDeclined) R.string.tx_grand_permissions
                else R.string.tx_ok
            ),
                onClick = { if (isPermanentlyDeclined) onGoToAppSettingsClick() else onOkClick() })
        },
        title = {
            DialogText(text = stringResource(id = R.string.tx_permission_request))
        },
        text = {
            DialogText(
                text = stringResource(
                    id = permissionTextProvides.getDescription(
                        isPermanentlyDeclined = isPermanentlyDeclined, context = context
                    )
                )
            )
        },
        containerColor = DialogBackgroundColor,
        properties = DialogProperties(dismissOnClickOutside = false),
        modifier = modifier.border(
            width = dp2,
            brush = getGradient(MusicTopMixItemBackgroundColor, MainCardColor),
            shape = RoundedCornerShape(dp26)
        ),
    )
}

@Composable
fun GradientButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = modifier
                .background(
                    brush = getGradient(ButtonGradientStartColor, ButtonGradientEndColor),
                    shape = RoundedCornerShape(dp32)
                )
                .border(
                    width = dp2,
                    brush = getGradient(ButtonGradientEndColor, ButtonGradientStartColor),
                    shape = RoundedCornerShape(dp32)
                ),
        ) {
            DialogText(text = text,
                modifier = modifier
                    .clickable { onClick() }
                    .padding(vertical = dp8, horizontal = dp21))
        }
    }
}

private fun getGradient(first: Color, second: Color) =
    Brush.horizontalGradient(colors = listOf(first, second))

@Composable
private fun DialogText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = MainTextColor,
        modifier = modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

interface PermissionTextProvides {
    fun getDescription(isPermanentlyDeclined: Boolean, context: Context): Int
}

class AudioPermissionTextProvides : PermissionTextProvides {
    override fun getDescription(isPermanentlyDeclined: Boolean, context: Context): Int =
        if (isPermanentlyDeclined) R.string.tx_audio_permission_is_permanently_declined_true
        else R.string.tx_audio_permission_is_permanently_declined_false
}