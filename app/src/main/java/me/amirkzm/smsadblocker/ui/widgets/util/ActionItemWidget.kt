package me.amirkzm.smsadblocker.ui.widgets.util

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import me.amirkzm.smsadblocker.resources.blockSmsAdsText

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ActionItem(
    modifier: Modifier = Modifier,
    iconData: Painter,
    onClick: () -> Unit,
) {
    val primary = MaterialTheme.colors.primary
    Card(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = primary,
        onClick = onClick,
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = iconData,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = blockSmsAdsText)
        }
    }
}