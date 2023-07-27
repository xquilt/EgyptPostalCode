package com.polendina.egyptpostalcode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.polendina.egyptpostalcode.ui.theme.EgyptPostalCodeTheme

@Composable
fun OfficeCard(
    office: Office,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(200.dp)
            .width(370.dp)
    ) {
//        Text(
//            text = office.office
//        )
        // todo: This should open up Google maps apps or something
        InfoRow(
            category = stringResource(id = R.string.address),
            value = office.address,
            imageVector = Icons.Default.LocationOn
        )
        InfoRow(
            category = stringResource(id = R.string.postal_code),
            value = office.postal_code,
            // todo: Need a suitable icon
            imageVector = Icons.Filled.Email
        )
        // todo: This should open up the dialing app
        InfoRow(
            category = stringResource(id = R.string.phone_number),
            value = office.tel,
            imageVector = Icons.Filled.Call
        )
        // todo: The whole row could be enhanced entirely (instead of replicating the original design)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 20.dp,
                    horizontal = 10.dp
                )
        ) {
            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = office.visits
                )
                // todo: Surely the icon isn't suitable
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
            ) {
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 10.dp)
                    )
                }
                Text(
                    text = stringResource(id = R.string.share)
                )
            }
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
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 10.dp,
                horizontal = 20.dp
            )
    ) {
        Text(
            text = value
        )
        Text(
            text = category,
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
}

@Preview(showBackground = true)
@Composable
private fun OfficeCardPreview() {
    EgyptPostalCodeTheme() {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            for (office in offices) {
                OfficeCard(
                    office = office,
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }
    }
}
