package com.polendina.egyptpostalcode

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material.icons.outlined.Minimize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfficeBottomSheet(
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = { /*TODO*/ },
    ) {
        LazyColumn() {
            items(items = OfficeFeatures.values()) {
//                Box (
//                    modifier = Modifier
//                        .padding(20.dp)
//                        .border(1.dp, Color.White)
//                ) {
                    Row (
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Icon(
                            imageVector = if (it.available) Icons.Outlined.CheckCircleOutline else Icons.Outlined.Minimize,
                            contentDescription = null,
                            tint = if (it.available) Color.Green else Color.Red
                        )
                        Row {
                            Text(
                                text = stringResource(id = it.stringResource),
                                modifier = Modifier,
                                style = TextStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 15.sp
                                )
                            )
                            Icon(
                                imageVector = it.icon,
                                contentDescription = null,
                            )
                        }
                    }
//                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListingItem() {
    Surface (
        modifier = Modifier
            .fillMaxSize()
    ) {
        OfficeBottomSheet()
    }
}