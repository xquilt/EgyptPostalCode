package com.polendina.egyptpostalcode

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.polendina.egyptpostalcode.presentation.ScreensViewModel
import com.polendina.egyptpostalcode.presentation.navigation.BottomNavigationBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    activeState: Boolean,
    screensViewModel: ScreensViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
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
            ) { }
        }
    ) {
        val localCurrentContext = LocalContext.current
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    paddingValues = it
                )
        ) {
            items(items = screensViewModel.officeList) {office ->
                OfficeCard(
                    office = office,
                    cardOnClick = {
                        coroutineScope.launch {
                            screensViewModel.obtainOffice(it.id.toInt())
                            bottomSheetState.show()
                        }
                    },
                    shareOnClick = {},
                    callOnClick = { phoneNumber ->
                        localCurrentContext.startActivity(Intent(
                            Intent.ACTION_DIAL,
                            Uri.parse("tel:${phoneNumber}")
                        ))
                    },
                    modifier = Modifier
                        .padding(all = 10.dp)
                )
            }
        }
    }
    AnimatedVisibility(visible = bottomSheetState.isVisible) {
        OfficeBottomSheet(
            sheetState = bottomSheetState,
            onDissmissRequest = {
                coroutineScope.launch {
                    bottomSheetState.hide()
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    Scaffold (
        bottomBar = {
            BottomNavigationBar(navController = rememberNavController())
        }
    ) {
        SearchScreen(
            activeState = true,
            modifier = Modifier
                .padding(it)
        )
    }
}