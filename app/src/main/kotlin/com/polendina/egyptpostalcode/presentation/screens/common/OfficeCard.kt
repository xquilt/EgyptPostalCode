package com.polendina.egyptpostalcode

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MarkunreadMailbox
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.MapsHomeWork
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.polendina.egyptpostalcode.domain.model.PostOffice
import com.polendina.egyptpostalcode.ui.theme.EgyptPostalCodeTheme

@Composable
fun OfficeCard(
    office: PostOffice,
    shareOnClick: () -> Unit,
    cardOnClick: (PostOffice) -> Unit,
    callOnClick: (phoneNumber: Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .height(200.dp)
            .width(370.dp)
            .clickable {
                cardOnClick(office)
            }
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
                // TODO: This should open up Google maps apps or something
                value = null,
                imageVector = Icons.Outlined.MapsHomeWork,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        InfoRow(
            category = stringResource(id = R.string.address),
            // TODO: This should open up Google maps apps or something
            value = office.address,
            imageVector = Icons.Outlined.LocationOn,
            modifier = Modifier
                .fillMaxWidth()
        )
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
            // TODO: This should open up the dialing app
            InfoRow(
                category = stringResource(id = R.string.phone_number),
                value = office.tel,
                clickCallBack = {
                    // TODO: There should be a way to account of parsing exceptions
                    callOnClick(office.tel.toLong())
                },
                imageVector = Icons.Filled.Call
            )
        }
    }
}

@Composable
private fun InfoRow(
    category: String,
    value: String?,
    imageVector: ImageVector,
    clickCallBack: () -> Unit = {},
    modifier: Modifier = Modifier,
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
                // TODO: This should be relocated to Typography file
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Default,
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                modifier = Modifier
                    .padding(start = 10.dp)
            )
            IconButton(onClick = clickCallBack) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onErrorContainer,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
        }
        value?.let {
            Text(
                text = value,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                ),
                modifier = Modifier
                    .padding(end = 30.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OfficeCardPreview() {
    val localCurrentContext = LocalContext.current
    offices.first().let {
        OfficeCard(
            office = it,
            cardOnClick = {},
            shareOnClick = {
                Log.d("URL", it.link)
            },
            callOnClick = { phoneNumber ->
                localCurrentContext.startActivity(
                    Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:${phoneNumber}")
                ))
            },
            modifier = Modifier
                .padding(10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OfficeCardColumnPreview() {
    EgyptPostalCodeTheme() {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(offices) {
                OfficeCard(
                    office = it,
                    cardOnClick = {},
                    shareOnClick = {
                        Log.d("URL", it.link)
                    },
                    callOnClick = {},
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }
    }
}
