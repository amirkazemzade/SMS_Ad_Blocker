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


class BlockerViewModel : ViewModel() {
    private val _state = MutableStateFlow<BlockerState>(BlockerState.Initial)
    val state: StateFlow<BlockerState> = _state


    fun resetState() {
        _state.value = BlockerState.Initial
    }

    fun blockAdNumbers(context: Context) {
        _state.value = BlockerState.Loading(progress = 0.0f)
        viewModelScope.launch(Dispatchers.IO) {
            insertAdNumbersToBlockList(context)
            _state.value = BlockerState.Success
        }
    }

    private fun insertAdNumbersToBlockList(context: Context) {
        for (i in 0..999) {
            _state.value = BlockerState.Loading(progress = i.toFloat() / 999)
            val adNumber = "735${"%03d".format(i)}"
            blockNumber(context, adNumber)
        }
    }

    private fun deleteAdNumbersFromBlockList(context: Context) {
        for (i in 0..999) {
            _state.value = BlockerState.Loading(progress = i.toFloat() / 999)
            val adNumber = "735${"%03d".format(i)}"
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
