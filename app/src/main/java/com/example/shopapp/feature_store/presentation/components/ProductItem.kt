package com.example.notes.feature_product.presentation.components

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.shopapp.R
import com.example.shopapp.feature_store.data.entity.ProductEntity
import com.example.shopapp.feature_store.presentation.product.ProductViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel,
    product: ProductEntity,
    navigator: NavController,
    onItemClick: (ProductEntity) -> Unit,
    selected: Boolean=false
) {


//    SideEffect {
//        println("Composed ProductItem: ${product.id}")
//    }
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = modifier
                .padding(4.dp)
                .clickable {
                    onItemClick(product)
//                    visibleState = !visibleState
                },

            backgroundColor = Color.White,
            elevation = 2.dp,

            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = product.image)
                            .apply(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.ic_placeholder)
                                crossfade(true)
                            }).build()
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .align(CenterHorizontally),
                    contentScale = ContentScale.Inside
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = product.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.category,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraLight
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$${product.price}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )


                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}