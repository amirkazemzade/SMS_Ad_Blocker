package me.amirkzm.smsadblocker.ui.widgets.blocker

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import me.amirkzm.smsadblocker.resources.blockSmsAdsText
import me.amirkzm.smsadblocker.resources.smsAdsBlockSuccessText
import me.amirkzm.smsadblocker.ui.widgets.util.Center


@Composable
fun BlockerWidget(
    modifier: Modifier,
    viewModel: BlockerViewModel = viewModel(),
) {
    val context = LocalContext.current
    when (val state = viewModel.state.collectAsState().value) {
        is BlockerState.Initial -> Center(modifier = modifier) {
            Button(
                onClick = {
                    viewModel.blockAdNumbers(context)
                },
            ) {
                Text(text = blockSmsAdsText)
            }
        }
        is BlockerState.Loading -> Center(modifier = modifier) {
            CircularProgressIndicator(progress = state.progress)
        }
        is BlockerState.Success -> Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = smsAdsBlockSuccessText)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                viewModel.blockAdNumbers(context)
            }) {
                Text(text = blockSmsAdsText)
            }
        }
        is BlockerState.Error -> Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = state.message)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                viewModel.blockAdNumbers(context)
            }) {
                Text(text = blockSmsAdsText)
            }
        }
    }
}
