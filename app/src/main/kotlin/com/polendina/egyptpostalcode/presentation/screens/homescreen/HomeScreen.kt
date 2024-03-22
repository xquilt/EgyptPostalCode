package com.polendina.egyptpostalcode.presentation.homescreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlignHorizontalRight
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.polendina.egyptpostalcode.presentation.navigation.BottomNavigationBar
import com.polendina.egyptpostalcode.R
import com.polendina.egyptpostalcode.offices
import com.polendina.egyptpostalcode.presentation.navigation.Screen
import com.polendina.egyptpostalcode.presentation.screens.homescreen.components.FormerPostOfficeDisplay
import com.polendina.egyptpostalcode.presentation.screens.homescreen.components.FormerQueries
import com.polendina.egyptpostalcode.presentation.screens.homescreen.components.SectionHeader
import com.polendina.egyptpostalcode.presentation.screens.homescreen.components.ShipmentService

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
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
                    onClick = {
                        navController.navigate(Screen.Contact.route)
                    }
                ) {
                   Icon(
                       imageVector = Icons.Default.AlignHorizontalRight,
                       contentDescription = null
                   )
                }
            }
        }
    ) {
        // TODO: This should be refactored to a lazyColumn, but wait those items should be wrapped in item, and the scrollable section be inside of items! : )
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(10.dp)
        ) {
            // TODO: Another (blue?) clickable header at the opposite side titled "All Locations" for example would be cool.
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
            Row (
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ShipmentService(
                    onClickCallback = { /*TODO*/ },
                    painter = painterResource(id = R.drawable.gps)
                )
                ShipmentService(
                    onClickCallback = { /*TODO*/ },
                    painter = painterResource(id = R.drawable.shipment)
                )
            }
            SectionHeader(
                text = stringResource(id = R.string.placeholder),
                modifier = Modifier
                    .padding(vertical = 10.dp)
            )
            // TODO: Another (blue?) clickable header at the opposite side titled "All Locations" for example would be cool.
            FormerQueries(items = offices) {
                FormerPostOfficeDisplay(
                    postOffice = it,
                    postOfficeCardCallback = { },
                    postOfficeIdCallback = { }
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Scaffold (
        bottomBar = {
            BottomNavigationBar(navController = rememberNavController())
        }
    ) {
        HomeScreen(
            modifier = Modifier.padding(it),
            navController = rememberNavController()
        )
    }
}