package com.polendina.egyptpostalcode

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.polendina.egyptpostalcode.domain.model.PostOffice
import com.polendina.egyptpostalcode.presentation.ScreensViewModel
import com.polendina.egyptpostalcode.ui.theme.EgyptPostalCodeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    activeState: Boolean,
    screensViewModel: ScreensViewModel = viewModel(),
    modifier: Modifier
) {
    Scaffold (
        topBar = {
            SearchBar(
                query = screensViewModel.query.value,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_office)
                    )
                },
                onQueryChange = {
                    screensViewModel.updateQuery(it)
                },
                onSearch = {
                    screensViewModel.getOffices(it)
                },
                active = activeState,
                onActiveChange = {},
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
            offices = screensViewModel.officeList,
            modifier = modifier
        )
    }
}

@Composable
private fun OfficesList(
    paddingValues: PaddingValues,
    modifier: Modifier,
    offices: List<PostOffice>,
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
                shareOnClick = {},
                modifier = modifier
                    .padding(all = 10.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    EgyptPostalCodeTheme {
        HomeScreen(
            activeState = true,
            modifier = Modifier
        )
    }
}