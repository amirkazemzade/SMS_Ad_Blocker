package me.amirkzm.smsadblocker.ui.widgets.default_cheker

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import me.amirkzm.smsadblocker.resources.notDefaultWarningText
import me.amirkzm.smsadblocker.resources.setAsDefaultText
import me.amirkzm.smsadblocker.ui.widgets.blocker.BlockerWidget


@Composable
fun DefaultChecker(
    modifier: Modifier = Modifier,
    viewModel: DefaultCheckerViewModel = viewModel(),
) {
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = { result ->
                viewModel.setDefaultDialerState(result.resultCode == Activity.RESULT_OK)
            },
        )
    when (viewModel.defaultDialerState.collectAsState().value) {
        is DefaultDialerState.Initial -> {
            viewModel.checkDefaultDialer(context)
            CircularProgressIndicator(modifier = modifier)
        }
        is DefaultDialerState.Default -> {
            BlockerWidget(modifier = modifier)
        }
        is DefaultDialerState.NotDefault -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = notDefaultWarningText)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    viewModel.changeDefaultDialer(context, launcher)
                }) {
                    Text(text = setAsDefaultText)
                }
            }
        }
    }
}