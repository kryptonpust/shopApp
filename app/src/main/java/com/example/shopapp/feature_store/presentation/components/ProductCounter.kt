package com.example.shopapp.feature_store.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductCounter(
    modifier: Modifier=Modifier,
    startText:String="",
    counter:Long=0L,
    endText: String="",
    vertical:Boolean=false,
    onButtonClick:(Long)->Unit
//    viewModel: ProductViewModel,
){

    Card(
        modifier= modifier,
        elevation = 5.dp,
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color(0xFFFFCCE4)
    ) {
        if(vertical)
        {
            Column(modifier = Modifier.padding(4.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                PositiveButton(counter = counter, onButtonClick = onButtonClick)
                CounterText(startText = startText, counter = counter, endText = endText)
                NegativeButtonWithText(counter = counter, onButtonClick =onButtonClick)
            }
        }else {
            Row(
                modifier = Modifier.padding(4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(counter>0)
                {
                    NegativeButtonWithText(counter = counter, onButtonClick =onButtonClick)
                    CounterText(startText = startText, counter = counter, endText = endText)
                }
                PositiveButton(counter = counter, onButtonClick = onButtonClick)

            }
        }
    }
}

@Composable
fun NegativeButtonWithText(
    counter:Long,
    onButtonClick: (Long) -> Unit)
{

        OutlinedButton(
            modifier=Modifier.size(20.dp),
            enabled=counter>1,
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFBFDD)),
            border = BorderStroke(0.dp, Color.Transparent),

            onClick = { onButtonClick(counter-1) }
        ) {
            Text(text = "-", color = Color.White)
        }

}
@Composable
fun CounterText(startText: String,counter: Long,endText: String)
{
    Text(text = startText+counter+endText,
        modifier = Modifier.padding(4.dp),
//                style = MaterialTheme.typography.displaySmall,
        fontSize = 12.sp
    )
}
@Composable
fun PositiveButton(counter: Long,onButtonClick: (Long) -> Unit)
{
    OutlinedButton(
        modifier=Modifier.size(20.dp),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF6200EE)),
        border = BorderStroke(0.dp, Color.Transparent),
        onClick = {
            onButtonClick(counter+1)
        }
    ) {
        Text(text = "+", color = Color.White)
    }
}

//@Composable
//@Preview
//fun ProductPreview(){
//    Column(
//    horizontalAlignment = Alignment.CenterHorizontally)
//    {
//        Card(modifier = Modifier
//            .fillMaxWidth()
//            .height(80.dp)) {
//
//        }
//        ProductCounter(productId = -1, text="5", viewModel = viewModel)
//    }
//}