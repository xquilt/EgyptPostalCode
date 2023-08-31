package com.polendina.egyptpostalcode.presentation.homescreen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.polendina.egyptpostalcode.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProvinceHorizontalIndicator(
    onProvinceClickCallback: (Province) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState { Province.entries.size }
    HorizontalPager(
        state = pagerState
    ) {
        Province.entries[it].let {
            Box (
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Red)
                    .height(200.dp)
                    .fillMaxWidth()
                    .clickable {
                        onProvinceClickCallback(it)
                    }
            ) {
                Image(
                    painter = painterResource(it.image),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}

enum class Province(
    val provinceName: String,
    @DrawableRes val image: Int
) {
    ALEXANDRIA("Alexandria", R.drawable.alexandria),
    ASWAN("Aswan", R.drawable.aswan),
    KAFRELSKEISH("Kafr El-Skeish", R.drawable.kafr_elskeish)
}

@Preview(showBackground = true)
@Composable
fun ProvinceHorizontalIndicatorPreview() {
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        ProvinceHorizontalIndicator(
            onProvinceClickCallback = {}
        )
    }
}