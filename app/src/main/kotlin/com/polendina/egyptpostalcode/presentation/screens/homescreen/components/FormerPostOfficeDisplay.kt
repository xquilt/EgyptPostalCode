package com.polendina.egyptpostalcode.presentation.screens.homescreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.polendina.egyptpostalcode.domain.model.PostOffice
import java.util.function.IntToDoubleFunction

/**
 * Vertical column displaying formerly loaded post offices.
 *
 * @param modifier Modifier to be applied to the first nested parent composable.
 * @param items List of the previously loaded post offices.
 * @param content A composable responsible for display the post office metadata.
 */
@Composable
fun FormerQueries(
    modifier: Modifier = Modifier,
    items: List<PostOffice>, // TODO: Load post offices from a Room database.
    content: @Composable LazyListScope.(PostOffice) -> Unit
) {
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val lazyListScope = this // FIXME: Janky implementation
        items(items) {
            content(lazyListScope, it)
        }
    }
}

/**
 * Display previously loaded post offices that were saved at local database.
 *
 * @param postOffice The previously loaded post office.
 * @param modifier Modifier to be applied to the nested parent composable.
 */
@Composable
fun FormerPostOfficeDisplay(
    postOffice: PostOffice,
    modifier: Modifier = Modifier,
    postOfficeCardCallback: (PostOffice) -> Unit,
    postOfficeIdCallback: (String) -> Unit
) {
    Card (
        modifier = modifier
            .height(70.dp)
            .fillMaxWidth()
            .clickable { postOfficeCardCallback(postOffice) }
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box (
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxHeight()
                    .width(70.dp)
                    .background(MaterialTheme.colorScheme.tertiary)
                    .clickable { postOfficeIdCallback(postOffice.id) } // TODO: This should be assigned a clipboard paste to or something.
            ) {
                Text(
                    text = postOffice.id,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
            Column (
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .padding(15.dp)
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = postOffice.address,
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.SemiBold
                        ),
                    )
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.outline
                    )
                }
                Text(
                    text = postOffice.tel,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.outline,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier
                        .padding(end = 25.dp)
                )
            }
        }
    }
}