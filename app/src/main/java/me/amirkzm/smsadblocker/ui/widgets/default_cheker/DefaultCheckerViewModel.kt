package me.amirkzm.smsadblocker.ui.widgets.default_cheker

import android.app.role.RoleManager
import android.content.Context
import android.content.Context.ROLE_SERVICE
import android.content.Intent
import android.os.Build
import android.telecom.TelecomManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import me.amirkzm.smsadblocker.resources.packageNameText


class DefaultCheckerViewModel : ViewModel() {

    private val _defaultDialerState =
        MutableStateFlow<DefaultDialerState>(DefaultDialerState.Initial)
    val defaultDialerState: StateFlow<DefaultDialerState> = _defaultDialerState

    fun setDefaultDialerState(isDefault: Boolean) {
        _defaultDialerState.value =
            if (isDefault) DefaultDialerState.Default else DefaultDialerState.NotDefault
    }

    fun checkDefaultDialer(
        context: Context,
    ) {
        val telecomManager =
            context.getSystemService(Context.TELECOM_SERVICE) as TelecomManager
        val defaultDialerPackage = telecomManager.defaultDialerPackage
        val isAlreadyDefaultDialer = packageNameText == defaultDialerPackage
        setDefaultDialerState(isAlreadyDefaultDialer)
    }

    fun changeDefaultDialer(
        context: Context,
        launcher: ManagedActivityResultLauncher<Intent, ActivityResult>,
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val roleManager = context.getSystemService(ROLE_SERVICE) as RoleManager?
            val intent = roleManager!!.createRequestRoleIntent(RoleManager.ROLE_DIALER)
            launcher.launch(intent)
            return
        }
        val intent = Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER).apply {
            putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME, packageNameText)
        }
        launcher.launch(intent)
    }
}


sealed interface DefaultDialerState {
    object Initial : DefaultDialerState
    object Default : DefaultDialerState
    object NotDefault : DefaultDialerState
}