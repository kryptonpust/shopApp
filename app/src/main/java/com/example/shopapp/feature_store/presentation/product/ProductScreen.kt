package com.example.shopapp.feature_store.presentation.product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notes.feature_product.presentation.components.ProductItem


@Composable
fun ProductScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel(),
) {
    Scaffold(
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
                        //TODO
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCartCheckout,
                            contentDescription = ""
                        )
                    }
                }

            )
        }
    ) { padding ->
        val productsState = viewModel.productState.value
        Column(
            modifier = Modifier
                .fillMaxSize()
//            .background(Color.Red)
                .padding(padding)
        )
        {
            Spacer(modifier = Modifier.height(8.dp))
//            SearchBar(onSearchAction = {
//                viewModel.onEvent(ProductEvent.Search(it))
//            })
            Spacer(modifier = Modifier.height(8.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
//                modifier = Modifier.background(Color.Green)
            )
            {
                items(productsState.products.size) { idx ->
                    ProductItem(
                        viewModel = viewModel,
                        product = productsState.products[idx],
                        selected = if (productsState.selectedProduct == null) false else productsState.selectedProduct == productsState.products[idx],
                        navigator = navController,
                        onItemClick = { product ->
                            //TODO
                        },
                    )
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