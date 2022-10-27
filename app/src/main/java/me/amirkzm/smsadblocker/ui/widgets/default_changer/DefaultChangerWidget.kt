package me.amirkzm.smsadblocker.ui.widgets.default_changer

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import me.amirkzm.smsadblocker.resources.notDefaultWarningText
import me.amirkzm.smsadblocker.resources.setAsDefaultText


@Composable
fun DefaultChanger(
    modifier: Modifier = Modifier,
    viewModel: DefaultChangerViewModel = viewModel(),
    onResult: (Boolean) -> Unit,
) {
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = { result ->
                onResult(result.resultCode == Activity.RESULT_OK)
            },
        )
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