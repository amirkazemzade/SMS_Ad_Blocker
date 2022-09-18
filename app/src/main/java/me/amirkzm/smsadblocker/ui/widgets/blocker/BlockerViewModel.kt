package me.amirkzm.smsadblocker.ui.widgets.blocker

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.BlockedNumberContract.BlockedNumbers
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.DecimalFormat


class BlockerViewModel : ViewModel() {
    private val _state = MutableStateFlow<BlockerState>(BlockerState.Initial)
    val state: StateFlow<BlockerState> = _state


    fun blockAdNumbers(context: Context) {
        _state.value = BlockerState.Loading(progress = 0.0f)
        viewModelScope.launch(Dispatchers.IO) {
            insertAdNumbersToBlockList(context)
            _state.value = BlockerState.Success
        }
    }

    private fun insertAdNumbersToBlockList(context: Context) {
        val formatter = DecimalFormat("###")
        for (i in 0..999) {
            _state.value = BlockerState.Loading(progress = i.toFloat() / 999)
            val adNumber = "7356${formatter.format(i)}"
            blockNumber(context, adNumber)
        }
    }

    private fun deleteAdNumbersFromBlockList(context: Context) {
        val formatter = DecimalFormat("###")
        for (i in 0..999) {
            _state.value = BlockerState.Loading(progress = i.toFloat() / 999)
            val adNumber = "7356${formatter.format(i)}"
            val uri = blockNumber(context, adNumber)
            context.contentResolver.delete(uri!!, null, null)
        }
    }

    private fun blockNumber(
        context: Context,
        number: String,
    ): Uri? {
        val values = ContentValues()
        values.put(BlockedNumbers.COLUMN_ORIGINAL_NUMBER, number)
        return context.contentResolver.insert(BlockedNumbers.CONTENT_URI, values)
    }
}

sealed class BlockerState {
    object Initial : BlockerState()
    data class Loading(val progress: Float) : BlockerState()
    object Success : BlockerState()
    data class Error(val message: String) : BlockerState()
}
