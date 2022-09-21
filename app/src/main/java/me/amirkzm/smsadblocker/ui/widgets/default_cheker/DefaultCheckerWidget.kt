package me.amirkzm.smsadblocker.ui.widgets.default_cheker

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import me.amirkzm.smsadblocker.ui.widgets.actions.Actions
import me.amirkzm.smsadblocker.ui.widgets.default_changer.DefaultChanger


@Composable
fun DefaultChecker(
    modifier: Modifier = Modifier,
    viewModel: DefaultCheckerViewModel = viewModel(),
) {
    val context = LocalContext.current
    when (viewModel.defaultDialerState.collectAsState().value) {
        is DefaultDialerState.Initial -> {
            viewModel.checkDefaultDialer(context)
            CircularProgressIndicator(modifier = modifier)
        }
        is DefaultDialerState.Default -> Actions(modifier = modifier)
        is DefaultDialerState.NotDefault -> DefaultChanger(
            onResult = viewModel::setDefaultDialerState
        )
    }
}