package com.gramirez.calculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import java.text.NumberFormat
import java.util.Locale

class TipViewModel : ViewModel() {

    private val _billAmount = MutableStateFlow("")
    val billAmount: StateFlow<String> = _billAmount
    private val _tipPercentage = MutableStateFlow<Int?>(null)
    val tipPercentage: StateFlow<Int?> = _tipPercentage

    private val _customTipAmount = MutableStateFlow("")
    val customTipAmount: StateFlow<String> = _customTipAmount

    val totalToPay: StateFlow<String> = combine(
        _billAmount,
        _tipPercentage,
        _customTipAmount
    ) { billAmountStr, tipPercentage, customTipAmountStr ->

        val billAmount = billAmountStr.toDoubleOrNull() ?: 0.0
        val customTipAmount = customTipAmountStr.toDoubleOrNull()

        val tip = if (customTipAmountStr.isNotBlank() &&
                    customTipAmount != null &&
                    customTipAmount > 0
        ) {
            customTipAmount
        } else {
            billAmount * ((tipPercentage ?: 0) / 100.0)
        }

        val formatter = NumberFormat.getNumberInstance(Locale.US).apply {
            minimumFractionDigits = 2
            maximumFractionDigits = 2
        }

        formatter.format(billAmount + tip)
    }.stateIn(viewModelScope, SharingStarted.Companion.WhileSubscribed(5000), "0.00")


    fun setBillAmount(value: String) {
        _billAmount.value = value
    }

    fun setTipPercentage(value: Int?) {
        _tipPercentage.value = value
    }

    fun setCustomTipAmount(value: String) {
        _customTipAmount.value = value
    }

    fun reset() {
        _billAmount.value = ""
        _tipPercentage.value = null
        _customTipAmount.value = ""
    }
}