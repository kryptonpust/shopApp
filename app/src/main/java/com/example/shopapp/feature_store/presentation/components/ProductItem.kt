package com.example.shopapp.feature_store.presentation.components

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.shopapp.R
import com.example.shopapp.common.utils.Utils
import com.example.shopapp.feature_store.data.entity.CartEntity
import com.example.shopapp.feature_store.data.entity.ProductEntity
import com.example.shopapp.feature_store.presentation.cart.CartEvent
import com.example.shopapp.feature_store.presentation.product.ProductViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel,
    product: ProductEntity,
    onItemClick: (ProductEntity) -> Unit,
    selected: Boolean = false
) {


//    SideEffect {
//        println("Composed ProductItem: ${product.id}")
//    }
    Column(horizontalAlignment = CenterHorizontally,
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
                Spacer(modifier = Modifier.height(4.dp))
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

                Row(modifier=Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                    ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Price: ", fontWeight = FontWeight.SemiBold, fontSize = 10.sp, color = Color.Gray)
                        Text(
                            text = "৳${Utils.priceFormat(product.price)}",
                            color = Color(0xFFDA2079),
                            fontSize = 12.sp,
                            maxLines = 3,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = "৳${Utils.priceFormat(product.price)}",
                        color = Color(0xFFDA2079),
                        fontSize = 10.sp,
                        maxLines = 3,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(textDecoration = TextDecoration.LineThrough)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "category: ", fontWeight = FontWeight.SemiBold, fontSize = 10.sp, color = Color.Gray)
                    Text(
                        text = product.category,
                        color = Color(0xFF312ED1),
                        fontSize = 12.sp,
                        maxLines = 3,
                        fontWeight = FontWeight.Bold
                    )
                }


                Spacer(modifier = Modifier.height(12.dp))
            }
        }
        AnimatedVisibility(
            visible = selected,
            enter = scaleIn(),
            exit = scaleOut()
        )
        {


//            val quantity = viewModel.getQuantityForId(product.id).collectAsState(initial = 0)
            val cartEntity =viewModel.getCartForId(product.id).collectAsState(initial = CartEntity(null, productId = product.id,0)).value
            ProductCounter(
                modifier= Modifier
                    .height(30.dp)
                    .offset(y = (-15).dp),
                counter = cartEntity.quantity,
                endText = " পিস",
                onButtonClick = {
                    viewModel.onEvent(CartEvent.UpdateCart(CartEntity(id = cartEntity.id, productId=cartEntity.productId, quantity = it)))
                })
        }
    }
}