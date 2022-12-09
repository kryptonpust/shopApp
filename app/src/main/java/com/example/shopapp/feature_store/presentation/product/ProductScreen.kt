package com.example.shopapp.feature_store.presentation.product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shopapp.common.presentation.LoadingAnimation
import com.example.shopapp.common.utils.Screens
import com.example.shopapp.common.utils.UIEvents
import com.example.shopapp.feature_store.presentation.components.ProductItem
import com.example.shopapp.feature_store.presentation.components.SearchBar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun ProductScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val searchState = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true)
    {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UIEvents.SnackBarEvent -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = event.message,
                            duration = SnackbarDuration.Short
                        )
                    }
                }
                is UIEvents.NavigateEvent -> {
                    navController.navigate(event.route) {
                        popUpTo(0)
                    }
                }
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Shop",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = FontFamily.Cursive
                    )
                },
                actions = {
                    IconButton(onClick =
                    {
                        navController.navigate(Screens.CartScreen.route)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCartCheckout,
                            tint = Color(0xFFFFB300),
                            contentDescription = ""
                        )
                    }
                    IconButton(onClick =
                    {
                        viewModel.onEvent(ProductEvent.Logout())
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Logout,
                            tint = Color(0xFFF06292),
                            contentDescription = ""
                        )
                    }
                }

            )
        }
    ) { padding ->
        val productsState = viewModel.productState.value
        if (productsState.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoadingAnimation()
            }
        } else {

            Column(
                modifier = Modifier
                    .fillMaxSize()
//            .background(Color.Red)
                    .padding(padding)
            )
            {
                Spacer(modifier = Modifier.height(8.dp))
                if (productsState.products.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "No Product!!",
                            color = Color(0xFFDA2079),
                            fontWeight = FontWeight.Bold,
                            fontSize = 40.sp
                        )
                    }
                } else {
                    SearchBar(mutableState = searchState, onSearchAction = {
                        viewModel.onEvent(ProductEvent.Search(it))
                    })
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
//                modifier = Modifier.background(Color.Green)
                    )
                    {
                        items(productsState.products.size) { idx ->
                            ProductItem(
                                modifier = Modifier.width(200.dp),
                                viewModel = viewModel,
                                product = productsState.products[idx],
                                onItemClick = { product ->
                                    viewModel.onEvent(ProductEvent.ProductSelected(product))
                                },
                                selected = if (productsState.selectedProduct == null) false else productsState.selectedProduct == productsState.products[idx],
                            )
                        }
                    }
                }
            }

        }
    }
}

@Composable
@Preview
fun TestProductScreen() {
    ProductScreen(navController = rememberNavController())
}