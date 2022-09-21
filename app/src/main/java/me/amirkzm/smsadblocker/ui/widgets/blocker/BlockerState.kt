package me.amirkzm.smsadblocker.ui.widgets.blocker

sealed class BlockerState {
    object Initial : BlockerState()
    data class Loading(val progress: Float) : BlockerState()
    object Success : BlockerState()
    data class Error(val message: String) : BlockerState()
}