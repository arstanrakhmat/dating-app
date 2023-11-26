package com.example.datingjetpack.screens.onboarding

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.datingjetpack.widgets.CustomButton
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(navController: NavController) {

    val sliderList =
        listOf(
            "https://images.unsplash.com/photo-1619045119136-349759036541?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1517504734587-2890819debab?q=80&w=1639&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1519155751704-f00dce84d79b?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        )

    val titles = listOf(
        "Algorithm",
        "Matches",
        "Premium"
    )

    val descriptions = listOf(
        "Users going through a vetting process to ensure you never match with bots.",
        "We match you with people that have a" +
                "large array of similar interests.",
        "Sign up today and enjoy the first month" +
                "of premium benefits on us."
    )

    val pagerState = rememberPagerState()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 32.dp, bottom = 55.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val context = LocalContext.current
        HorizontalPager(
            count = sliderList.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 65.dp),
            modifier = Modifier
                .height(350.dp)
                .padding(bottom = 32.dp)
        ) {
            Card(shape = RoundedCornerShape(20.dp), modifier = Modifier.graphicsLayer {
                val pageOffset = calculateCurrentOffsetForPage(it).absoluteValue
                lerp(start = 0.85f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f))
                    .also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                alpha = lerp(start = 0.50f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f))
            }) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(sliderList[it])
                        .crossfade(true).scale(Scale.FILL).build(),
                    contentDescription = "onboarding"
                )
            }
        }

        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = titles[pagerState.currentPage],
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = Color(233, 64, 87, 255),
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, bottom = 32.dp),
            text = descriptions[pagerState.currentPage],
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )

        Row(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(sliderList.size) { it ->
                val color =
                    if (pagerState.currentPage == it) Color(233, 64, 87, 255) else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(6.dp)
                        .clip(CircleShape)
                        .size(8.dp)
                        .background(color)
                ) {

                }
            }
        }

        CustomButton(
            modifier = Modifier.padding(top = 32.dp, bottom = 20.dp),
            text = "Create an account",
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            onButtonClicked = {
                Toast.makeText(context, "Start registration", Toast.LENGTH_SHORT).show()
            }
        )

        val text = "Already have an account? Sign in"
        ClickableText(modifier = Modifier.padding(top = 30.dp), text =
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            ) {
                append("Already have an account? ")
            }
            withStyle(
                style = SpanStyle(
                    color = Color(233, 64, 87, 255),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                ),
            ) {
                append("Sign in")

                // Add a custom click event for the "Sign in" text
                addStringAnnotation(
                    tag = "Clickable text",
                    annotation = "dfjlshaf",
                    start = length - 6,
                    end = length
                )
            }
        }, onClick = { offset ->
            if (offset in (text.length - 6) until text.length) {
                Toast.makeText(context, "Sign In clicked", Toast.LENGTH_SHORT).show()
            }
        })

    }


}