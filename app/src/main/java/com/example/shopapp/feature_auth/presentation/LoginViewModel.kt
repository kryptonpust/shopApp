package com.example.shopapp.feature_auth.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.common.utils.Resource
import com.example.shopapp.common.utils.Screens
import com.example.shopapp.common.utils.UIEvents
import com.example.shopapp.feature_auth.domain.use_case.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
private val authUseCase: AuthUseCase
):ViewModel() {

    private val _usernameState = mutableStateOf(TextFieldValue("mor_2314"))
    val usernameState: State<TextFieldValue> = _usernameState
    fun setUsername(value: String) {
        _usernameState.value = usernameState.value.copy(text = value)
    }

    private val _passwordState = mutableStateOf(TextFieldValue("83r5^_"))
    val passwordState: State<TextFieldValue> = _passwordState
    fun setPassword(value: String) {
        _passwordState.value = _passwordState.value.copy(text = value)
    }

    private val _rememberMeState = mutableStateOf(false)
    val rememberMeState: State<Boolean> = _rememberMeState
    fun setRememberMe(value: Boolean) {
        _rememberMeState.value = value
    }

    private val _logInState = mutableStateOf(false)
    val logInState: State<Boolean> = _logInState

    private val _autoLoginState = mutableStateOf(false)
    val autoLoginState: State<Boolean> = _autoLoginState

    private val _eventFlow = MutableSharedFlow<UIEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            when(authUseCase.autoLoginUseCase())
            {
                is Resource.Success->{
                    _eventFlow.emit(UIEvents.SnackBarEvent("Auto Login Success"))
                    delay(500)
                    _eventFlow.emit(UIEvents.NavigateEvent(Screens.ProductScreen.route, popUpTo = Screens.LoginScreen.route))
                }
                is Resource.Error->{
                    _autoLoginState.value=true
                }
                else -> {}
            }
        }
    }

    fun loginUser() {
        viewModelScope.launch {
            _logInState.value=true
            when(val result=authUseCase.loginUseCase(userName = usernameState.value.text, password = passwordState.value.text, rememberMe = rememberMeState.value))
            {
                is Resource.Success->{
                    _eventFlow.emit(UIEvents.SnackBarEvent("Login Success"))
                    _eventFlow.emit(UIEvents.NavigateEvent(Screens.ProductScreen.route,Screens.LoginScreen.route))
                    _logInState.value=false
                }
                is Resource.Error->{
                    _logInState.value=false
                    _eventFlow.emit(UIEvents.SnackBarEvent(result.message ?: "Unknown error occurred!")
                    )

                }
                else -> {}
            }
        }
    }
}