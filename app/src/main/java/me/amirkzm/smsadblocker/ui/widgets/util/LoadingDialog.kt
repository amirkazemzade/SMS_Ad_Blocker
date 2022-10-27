package me.amirkzm.smsadblocker.ui.widgets.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun LoadingDialog(
    progress: Float,
) {
    Dialog(onDismissRequest = {}) {
        Surface(
//            elevation = 4.dp,
//            color = MaterialTheme.colors.background,
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(
                modifier = Modifier.padding(16.dp),
            ) {
                CircularProgressIndicator(progress = progress)
            }
        }
    }
}