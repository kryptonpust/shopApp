package com.example.shopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shopapp.feature_auth.presentation.LoginScreen
import com.example.shopapp.common.utils.Screens
import com.example.shopapp.feature_store.presentation.cart.CartScreen
import com.example.shopapp.feature_store.presentation.product.ProductScreen
import com.example.shopapp.ui.theme.ShopAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screens.LoginScreen.route)
                    {
                        composable(route = Screens.LoginScreen.route)
                        {
                            LoginScreen(navController)
                        }
                        composable(route = Screens.ProductScreen.route)
                        {
                            ProductScreen(navController)
                        }
                        composable(route = Screens.CartScreen.route)
                        {
                            CartScreen(navController)
                        }
                    }
                }
            }
        }
    }


}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShopAppTheme {
        Greeting("Android")
    }
}