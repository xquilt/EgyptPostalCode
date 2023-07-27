package com.polendina.egyptpostalcode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.polendina.egyptpostalcode.ui.theme.EgyptPostalCodeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    activeState: Boolean,
    onActiveChange: (Boolean) -> Unit,
    modifier: Modifier
) {
    Scaffold (
        topBar = {
            SearchBar(
                query = query,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_office)
                    )
                },
                onQueryChange = onQueryChange,
                onSearch = onSearch,
                active = activeState,
                onActiveChange = onActiveChange,
                trailingIcon = {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null
                        )
                    }
                },
                modifier = Modifier
                    .height(90.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) {

            }
        },
        bottomBar = {
            BottomBar()
        }
    ) {
        OfficesList(
            paddingValues = it,
            modifier = modifier
        )
    }
}

@Composable
private fun OfficesList(
    paddingValues: PaddingValues,
    modifier: Modifier
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                paddingValues = paddingValues
            )
    ) {
        items(items = offices) {office ->
            OfficeCard(
                office = office,
                modifier = modifier
                    .padding(all = 10.dp)
            )
        }
    }
}

@Composable
private fun BottomBar() {
    BottomAppBar {
        Row (
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = null
                )
            }
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Outlined.DateRange,
                    contentDescription = null
                )
            }
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null
                )
            }
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    EgyptPostalCodeTheme {
        HomeScreen(
            query = "",
            onQueryChange = {},
            onSearch = {},
            activeState = true,
            onActiveChange = {},
            modifier = Modifier
        )
    }
}