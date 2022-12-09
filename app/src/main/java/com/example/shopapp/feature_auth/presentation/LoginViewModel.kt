package com.example.shopapp.feature_auth.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

):ViewModel() {

    private val _usernameState = mutableStateOf(TextFieldValue("mor_2314"))
    val usernameState: State<TextFieldValue> = _usernameState
    fun setUsername(value: String) {
        _usernameState.value = usernameState.value.copy(text = value)
    }

    private val _passwordState = mutableStateOf(TextFieldValue("83r5^d_"))
    val passwordState: State<TextFieldValue> = _passwordState
    fun setPassword(value: String) {
        _passwordState.value = _passwordState.value.copy(text = value)
    }

    private val _rememberMeState = mutableStateOf(false)
    val rememberMeState: State<Boolean> = _rememberMeState
    fun setRememberMe(value: Boolean) {
        _rememberMeState.value = value
    }

    private val _loginState = mutableStateOf(false)
    val loginState: State<Boolean> = _loginState

    private val _eventFlow = MutableSharedFlow<String>()
    val eventFlow = _eventFlow.asSharedFlow()

}