package com.example.shopapp.feature_store.presentation.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shopapp.feature_store.data.entity.Cart
import com.example.shopapp.feature_store.presentation.components.CartItem
import com.example.shopapp.feature_store.presentation.components.SearchBar


@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartViewModel = hiltViewModel()
) {

    val searchState = remember {
        mutableStateOf(TextFieldValue(""))
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "আমার ব্যাগ",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription ="" )
                    }
                }
            )
        }
    ) { padding->
        val cartState = viewModel.cartState.value
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding))
        {
            Spacer(modifier = Modifier.height(8.dp))

            if(cartState.productWithCart.isEmpty())
            {
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Empty Cart!!", color = Color(0xFFDA2079), fontWeight = FontWeight.Bold, fontSize = 40.sp)
                }
            }else{
                SearchBar(searchState,onSearchAction = {
                    viewModel.onEvent(CartEvent.Search(it))
                })
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn()
                {
                    items(cartState.productWithCart.size) { idx ->
                        CartItem(
                            productWithCart = cartState.productWithCart[idx],
                            modifier= Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .padding(4.dp),
                            onDeleteClicked = {
                                viewModel.onEvent(CartEvent.DeleteCart(it))
                            },
                            onButtonClicked = {
                                val cart=cartState.productWithCart[idx].cart
                                viewModel.onEvent(CartEvent.UpdateCart(Cart(cart.id,cart.productId,it)))
                            }
                        )
                    }
                }
            }
        }
    }
}