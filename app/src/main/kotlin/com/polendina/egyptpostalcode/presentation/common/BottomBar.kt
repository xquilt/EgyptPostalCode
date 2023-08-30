package com.polendina.egyptpostalcode

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier
) {
    BottomAppBar (
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            NavItems.entries.forEach {
                IconButton(
                    onClick = it.onClick
                ) {
                    if (it.selected) {
                        Icon(
                            imageVector = it.imageVector,
                            contentDescription = it.contentDescription,
                            tint = MaterialTheme.colorScheme.onTertiary,
                            modifier = Modifier
                                .notificationDot()
                        )
                    } else {
                        Icon(
                            imageVector = it.imageVector,
                            contentDescription = it.contentDescription,
                        )
                    }
                }
            }
        }
    }
}

private fun Modifier.notificationDot(): Modifier =
    composed {
        val tertiary = MaterialTheme.colorScheme.tertiary
        drawWithContent {
            drawCircle(
                color = tertiary,
                radius = 20.dp.toPx(),
            )
            drawContent()
        }
    }

enum class NavItems(
    val imageVector: ImageVector,
    val contentDescription: String?,
    val selected: Boolean,
    val onClick: () -> Unit
) {
    // TODO: The logic decision of determining the selected icon is yet to be implemented
    HOME(Icons.Outlined.Home, null, false, {}),
    SHIPMENT(Icons.Outlined.LocalShipping, null, true, {}),
    LOCATION(Icons.Outlined.LocationOn, null, false, {}),
    FAVORITE(Icons.Outlined.FavoriteBorder, null, false, {})
}

@Preview(showBackground = true)
@Composable
private fun PreviewBottomBar() {
    Column (
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
    ) {
        BottomNavigationBar(
            modifier = Modifier
                .padding(10.dp)
        )
    }
}
