package com.example.shopapp.feature_auth.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shopapp.common.presentation.LoadingAnimation
import com.example.shopapp.common.utils.UIEvents
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val usernameState = viewModel.usernameState.value
    val passwordState = viewModel.passwordState.value
    val rememberMeState = viewModel.rememberMeState.value

    val autoLoginState = viewModel.autoLoginState.value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event){
                is UIEvents.SnackBarEvent->{
                    scope.launch{
                        snackbarHostState.showSnackbar(
                            message = event.message,
                            duration = SnackbarDuration.Short
                        )
                    }
                }
                is UIEvents.NavigateEvent->{
                    navController.navigate(event.route){
                        event.popUpTo?.let {
                            popUpTo(it)
                            {
                                inclusive=true
                            }

                        }
                    }
                }
            }

        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },

    ) { padding->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(64.dp))

            Column(
                Modifier.padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = CenterHorizontally
            ) {
                Text(text = "Welcome Back", fontSize = 40.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Cursive)
                if(autoLoginState){
                    Text(
                        text = "Login to your account to continue shopping",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        fontFamily = FontFamily.Cursive

                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            if(autoLoginState) {
//                Card(modifier = Modifier.fillMaxSize()) {
                    Column {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = usernameState.text,
                            onValueChange = {
                                viewModel.setUsername(it)
                            },
                            label = {
                                Text(text = "Username")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                            ),

                            )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Column {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = passwordState.text,
                            onValueChange = {
                                viewModel.setPassword(it)
                            },
                            label = {
                                Text(text = "Password")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                            ),

                            )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(checked = rememberMeState, onCheckedChange = {
                                viewModel.setRememberMe(it)
                            })
                            Text(text = "Remember me", fontSize = 12.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = {
                            viewModel.loginUser()
                        },
                        shape = RoundedCornerShape(8)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp), text = "Sign In", textAlign = TextAlign.Center
                        )
                    }
                }
            else{
                LoadingAnimation()
            }
            Spacer(modifier = Modifier.height(24.dp))

        }
    }
}

@Preview
@Composable
fun LoginScreenUITest()
{
    LoginScreen(rememberNavController())
}

