package com.polendina.egyptpostalcode.presentation.screens.contact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.polendina.egyptpostalcode.BottomNavigationBar
import com.polendina.egyptpostalcode.R

@Composable
fun Contact(
    modifier: Modifier = Modifier,
    contactViewModel: ContactViewModel = viewModel()
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column (
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            listOf(R.string.contact, R.string.our, R.string.dev).forEach {
                Text(
                    text = stringResource(id = it),
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 50.sp,
                        drawStyle = if (it != R.string.dev) Stroke(
                            miter = 10f,
                            width = 5f,
                            join = StrokeJoin.Round
                        ) else Stroke(width = 0f)
                    )
                )
            }
        }
        ContactItem.entries.forEach {
            if (it == ContactItem.MESSAGE) {
                ContactItemComp(
                    contactItem = it,
                    modifier = Modifier
                        .height(120.dp)
                )
                return@forEach
            }
            ContactItemComp(contactItem = it)
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colorScheme.secondary,
                disabledBackgroundColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            enabled = contactViewModel.submitButtonEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .height(70.dp)
        ) {
            Text(
                text = stringResource(id = R.string.submit),
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.know_more))
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.website),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "ContactForm")
@Composable
fun ContactPreview() {
    Scaffold (
        bottomBar = {
            BottomNavigationBar(navController = NavController(LocalContext.current))
        },
        modifier = Modifier
            .fillMaxSize()
    ){
        Contact(
            modifier = Modifier
                .padding(it)
        )
    }
}