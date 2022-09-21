package me.amirkzm.smsadblocker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import me.amirkzm.smsadblocker.resources.smsAdsBlockerText
import me.amirkzm.smsadblocker.ui.theme.SMSAdBlockerTheme
import me.amirkzm.smsadblocker.ui.widgets.default_cheker.DefaultChecker
import me.amirkzm.smsadblocker.ui.widgets.util.LocalSnackbarHostState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SMSAdBlockerTheme {

                // A surface container using the 'background' color from the theme
                val scaffoldState = rememberScaffoldState()

                CompositionLocalProvider(
                    LocalLayoutDirection provides LayoutDirection.Rtl,
                    LocalSnackbarHostState provides scaffoldState.snackbarHostState
                ) {
                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            val appBarColor = MaterialTheme.colors.primary
                            TopAppBar(
                                backgroundColor = appBarColor,
                                contentColor = contentColorFor(backgroundColor = appBarColor)
                            ) {
                                Text(smsAdsBlockerText)
                            }
                        }
                    ) {
                        DefaultChecker(modifier = Modifier.padding(it))
                    }
                }
            }
        }
    }
}