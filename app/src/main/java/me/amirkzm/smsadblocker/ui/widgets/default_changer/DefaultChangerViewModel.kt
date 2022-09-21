package me.amirkzm.smsadblocker.ui.widgets.default_changer

import android.app.role.RoleManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telecom.TelecomManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import me.amirkzm.smsadblocker.resources.packageNameText

class DefaultChangerViewModel : ViewModel(){

    fun changeDefaultDialer(
        context: Context,
        launcher: ManagedActivityResultLauncher<Intent, ActivityResult>,
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val roleManager = context.getSystemService(Context.ROLE_SERVICE) as RoleManager?
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