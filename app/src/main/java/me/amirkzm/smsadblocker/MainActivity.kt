package me.amirkzm.smsadblocker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import me.amirkzm.smsadblocker.resources.smsAdsBlockerText
import me.amirkzm.smsadblocker.ui.theme.SMSAdBlockerThemeM3
import me.amirkzm.smsadblocker.ui.widgets.default_cheker.DefaultChecker
import me.amirkzm.smsadblocker.ui.widgets.util.LocalSnackbarHostState

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SMSAdBlockerThemeM3 {
                // A surface container using the 'background' color from the theme
                val snackbarHostState = remember { SnackbarHostState() }

                CompositionLocalProvider(
                    LocalLayoutDirection provides LayoutDirection.Rtl,
                    LocalSnackbarHostState provides snackbarHostState
                ) {
                    Scaffold(
                        snackbarHost = { SnackbarHost(snackbarHostState) },
                        topBar = {
                            TopAppBar(
                                title = { smsAdsBlockerText },
                            )
                        }
                    ) {
                        DefaultChecker(modifier = Modifier.padding(it))
                    }
                }
            }
        }
    }
}