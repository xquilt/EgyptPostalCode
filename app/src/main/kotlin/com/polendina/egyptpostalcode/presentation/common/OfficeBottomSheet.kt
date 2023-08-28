package com.polendina.egyptpostalcode

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.polendina.egyptpostalcode.presentation.OfficeFeatures
import com.polendina.egyptpostalcode.presentation.ScreensViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfficeBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onDissmissRequest: () -> Unit,
    screensViewModel: ScreensViewModel = viewModel()
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDissmissRequest,
    ) {
        LazyColumn {
            items(items = OfficeFeatures.entries.toList()) {
                FeatureRow(officeFeatures = it)
            }
        }
    }
}

@Composable
private fun FeatureRow(
    officeFeatures: OfficeFeatures
) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Icon(
            imageVector = if (officeFeatures.available) Icons.Outlined.CheckCircleOutline else Icons.Outlined.Minimize,
            contentDescription = null,
            tint = if (officeFeatures.available) Color.Green else Color.Red,
        )
        Row {
            Text(
                text = stringResource(id = officeFeatures.stringResource),
                modifier = Modifier,
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            )
            Icon(
                imageVector = officeFeatures.icon,
                contentDescription = null,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PreviewListingItem() {
    Surface (
        modifier = Modifier
            .fillMaxSize()
    ) {
        OfficeBottomSheet(
            onDissmissRequest = {},
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
        )
    }
}