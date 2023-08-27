package com.polendina.egyptpostalcode

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MarkunreadMailbox
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.MapsHomeWork
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.polendina.egyptpostalcode.domain.model.OfficeResponse
import com.polendina.egyptpostalcode.domain.model.PostOffice
import com.polendina.egyptpostalcode.ui.theme.EgyptPostalCodeTheme

@Composable
fun OfficeCard(
    office: PostOffice,
    shareOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(150.dp)
            .width(370.dp)
    ) {
        Box {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = null
                )
            }
            InfoRow(
                category = office.office,
                // todo: This should open up Google maps apps or something
                value = office.address,
                imageVector = Icons.Outlined.MapsHomeWork,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
        ) {
            InfoRow(
                category = stringResource(id = R.string.postal_code),
                value = office.postal_code,
                imageVector = Icons.Filled.MarkunreadMailbox
            )
            // todo: This should open up the dialing app
            InfoRow(
                category = stringResource(id = R.string.phone_number),
                value = office.tel,
                imageVector = Icons.Filled.Call
            )
        }
    }
}

@Composable
private fun InfoRow(
    category: String,
    value: String,
    imageVector: ImageVector,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(
                vertical = 10.dp,
                horizontal = 20.dp
            )
    ) {
        Row (
            horizontalArrangement = Arrangement.End,
            modifier = modifier
        ) {
            Text(
                text = category,
                // todo: This should be relocated to Typography file
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                ),
                modifier = Modifier
                    .padding(start = 10.dp)
            )
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
        }
        Text(
            text = value,
            modifier = Modifier
                .padding(end = 30.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OfficeCardPreview() {
    EgyptPostalCodeTheme() {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            offices.forEach {
                OfficeCard(
                    office = it,
                    shareOnClick = {
                        Log.d("URL", it.link)
                    },
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }
    }
}
