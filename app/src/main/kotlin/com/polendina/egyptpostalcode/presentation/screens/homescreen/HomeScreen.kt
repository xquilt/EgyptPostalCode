package com.polendina.egyptpostalcode.presentation.homescreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlignHorizontalRight
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.polendina.egyptpostalcode.BottomNavigationBar
import com.polendina.egyptpostalcode.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Scaffold (
        topBar = {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null
                )
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                   Icon(
                       imageVector = Icons.Default.AlignHorizontalRight,
                       contentDescription = null
                   )
                }
            }
        }
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(10.dp)
        ) {
            SectionHeader(
                text = stringResource(R.string.provinces),
                modifier = Modifier
                .padding(bottom = 10.dp)
            )
            ProvinceHorizontalIndicator(
                onProvinceClickCallback = {},
            )
            SectionHeader(
                text = stringResource(id = R.string.favorites),
                modifier = Modifier
                    .padding(vertical = 10.dp)
            )
            FormerPostOffices (items = (0..10).toList()) {
                JustMeh(
                    // TODO: A better approach is possible. I guess I'm over abstracting things btw (the lazy row could be just used directly in here)
                    content = it as Int
                )
            }
            SectionHeader(
                text = stringResource(id = R.string.placeholder),
                modifier = Modifier
                    .padding(vertical = 10.dp)
            )
            FormerPostOffices(items = (0..10).toList()) {
                AnotherMeh(
                    content = it as Int
                )
            }
        }
    }

}

@Composable
fun FormerPostOffices(
    modifier: Modifier = Modifier,
    items: List<Any>,
    composableContainer: @Composable LazyListScope.(Any) -> Unit
) {
    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val lazyListScope = this // TODO: Junky implementation
        items(items) {
            composableContainer(lazyListScope, it)
        }
    }
}

@Composable
fun JustMeh(
    content: Int,
    modifier: Modifier = Modifier
) {
   Card (
       modifier = modifier
           .height(150.dp)
           .width(200.dp)
   ) {
   }
}

@Composable
fun AnotherMeh(
    content: Int,
    modifier: Modifier = Modifier
) {
    Card (
        modifier = modifier
            .height(100.dp)
            .width(230.dp)
    ) {
    }
}

@Composable
fun SectionHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = TextStyle(
            fontWeight = FontWeight.SemiBold
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Scaffold (
        bottomBar = {
            BottomNavigationBar(navController = rememberNavController())
        }
    ) {
        HomeScreen(modifier = Modifier.padding(it))
    }
}