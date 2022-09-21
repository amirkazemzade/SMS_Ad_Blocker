package me.amirkzm.smsadblocker.ui.widgets.blocker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import me.amirkzm.smsadblocker.R
import me.amirkzm.smsadblocker.resources.smsAdsBlockSuccessText
import me.amirkzm.smsadblocker.ui.widgets.blocker.BlockerState.Error
import me.amirkzm.smsadblocker.ui.widgets.blocker.BlockerState.Success
import me.amirkzm.smsadblocker.ui.widgets.util.ActionItem
import me.amirkzm.smsadblocker.ui.widgets.util.LoadingDialog
import me.amirkzm.smsadblocker.ui.widgets.util.LocalSnackbarHostState


@Composable
fun BlockerWidget(
    modifier: Modifier = Modifier,
    viewModel: BlockerViewModel = viewModel(),
) {
    val context = LocalContext.current
    val snackbarHostState = LocalSnackbarHostState.current
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(key1 = state) {
        if (state is Success) {
            snackbarHostState.showSnackbar(
                message = smsAdsBlockSuccessText
            )
            viewModel.resetState()
        }

        if (state is Error) {
            snackbarHostState.showSnackbar(
                message = state.message
            )
            viewModel.resetState()
        }

    }

    if (state is BlockerState.Loading) {
        LoadingDialog(progress = state.progress)
    }

    ActionItem(
        modifier = modifier,
        iconData = painterResource(id = R.drawable.ic_baseline_block_24),
        onClick = { viewModel.blockAdNumbers(context) }
    )
}
