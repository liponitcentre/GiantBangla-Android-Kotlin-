package com.example.giantbangla.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.giantbangla.R

@Preview(showSystemUi = true)
@Composable
fun OnboardingTwo() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(393.dp)
            .height(802.dp)
            .background(color = Color.White)
    ) {

        //Rajib test commit
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(all = 25.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
        )
        {
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                modifier = Modifier
                    .height(140.dp)
                    .width(180.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                painter = painterResource(R.drawable.icon),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                "See Personalized offers by\n" +
                        "allowing activity tracking",
                color = colorResource(R.color.green),
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.W700
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Activity tracking lets us personalize the \n" +
                        "offers and deals you’ll seen Shoppers BD ",
                color = colorResource(R.color.black),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500
                )
            )
            Box(modifier = Modifier.height(176.dp))
            Spacer(modifier = Modifier.height(220.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            )
            {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Box(
                        modifier = Modifier
                            .height(5.dp)
                            .width(8.dp)
                            .background(
                                color = colorResource(R.color.grey),
                                shape = RoundedCornerShape(16.dp)
                            )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .height(5.dp)
                            .width(35.dp)
                            .background(
                                color = colorResource(R.color.green),
                                shape = RoundedCornerShape(16.dp)
                            )
                    )
                }
                Button(
                    onClick = {}, modifier = Modifier
                        .height(46.dp)
                        .width(210.dp), shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.green))
                ) {
                    Text(
                        "Next", color = colorResource(R.color.white),
                        style = TextStyle(
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.W500
                        )
                    )
                }

            }

        }

    }

}