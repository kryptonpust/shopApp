package com.example.shopapp.feature_store.presentation.components



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.shopapp.R
import com.example.shopapp.common.utils.Utils
import com.example.shopapp.feature_store.data.dto.ProductWithCart
import com.example.shopapp.feature_store.data.entity.Cart
import com.example.shopapp.feature_store.data.entity.ProductEntity

@Composable
fun CartItem(
    modifier: Modifier=Modifier,
    productWithCart: ProductWithCart,
    onDeleteClicked: (Long)->Unit={},
    onButtonClicked: (Long)->Unit={},
)
{
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        elevation = 3.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = productWithCart.product.image)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.ic_placeholder)
                            crossfade(true)
                        }).build()
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
                    .fillMaxHeight(),
                contentScale = ContentScale.Inside
            )
            Spacer(modifier = Modifier.width(5.dp))
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(5.dp)
            ) {
                Text(
                    text = productWithCart.product.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Unit Price: ", fontWeight = FontWeight.SemiBold, fontSize = 10.sp, color = Color.Gray)
                    Text(
                        text = "৳${Utils.priceFormat(productWithCart.product.price)}",
                        color = Color(0xFFDA2079),
                        fontSize = 18.sp,
                        maxLines = 3,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Total Price: ",fontWeight = FontWeight.Bold, fontSize = 10.sp, color = Color.Gray)
                    Text(
                        text = "৳${Utils.priceFormat(productWithCart.product.price.times(productWithCart.cart.quantity))}",
                        color = Color(0xFFDA2079),
                        fontSize = 18.sp,
                        maxLines = 3,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
            Spacer(modifier = Modifier.height(5.dp))
            Column(verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = {
                    productWithCart.cart.id?.let(onDeleteClicked)
                }) {
                    Icon(imageVector = Icons.Filled.Cancel, contentDescription = "", tint = Color(0xFFDA2079),)
                }
                ProductCounter(counter = productWithCart.cart.quantity, vertical = true, endText = "", onButtonClick = onButtonClicked)
            }
        }
    }
}

@Preview
@Composable
fun Test()
{

        CartItem(productWithCart = ProductWithCart(
            Cart(null, 1, 1),
            ProductEntity(-1, "OK",50f,"category","adfadsf","asdf")
        ),
            modifier= Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(4.dp),
        )


}