package com.polendina.egyptpostalcode.presentation.screens.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlignHorizontalRight
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.polendina.egyptpostalcode.presentation.navigation.BottomNavigationBar
import com.polendina.egyptpostalcode.R
import com.polendina.egyptpostalcode.offices
import com.polendina.egyptpostalcode.presentation.homescreen.components.ProvinceHorizontalIndicator
import com.polendina.egyptpostalcode.presentation.navigation.Screen
import com.polendina.egyptpostalcode.presentation.screens.homescreen.components.FormerPostOfficeDisplay
import com.polendina.egyptpostalcode.presentation.screens.homescreen.components.SectionHeader

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
        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(10.dp)
        ) {
            item {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    SectionHeader(
                        text = stringResource(R.string.provinces),
                    )
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Preview More")
                    }
                }
                ProvinceHorizontalIndicator(
                    onProvinceClickCallback = {},
                )
            }
            item {
                SectionHeader(
                    text = stringResource(id = R.string.outlets),
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
            }
            items(items = offices) {
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