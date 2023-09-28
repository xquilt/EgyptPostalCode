package com.polendina.egyptpostalcode.presentation.screens.contact

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Message
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.polendina.egyptpostalcode.R

@Composable
fun ContactItemComp(
    contactItem: ContactItem,
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        placeholder = {
            Text(
                text = stringResource(id = contactItem.title)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = contactItem.icon,
                contentDescription = null
            )
        },
        onValueChange = contactItem.onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .height(60.dp)
    )
}

enum class ContactItem(
    @StringRes val title: Int,
    val icon: ImageVector,
    val onValueChange: (String) -> Unit
) {
    NAME(R.string.name, Icons.Default.AccountCircle, onValueChange =  {
        ContactViewModel().name = it
    }),
    EMAIL(R.string.email, Icons.Default.AlternateEmail, onValueChange =  { }),
    MESSAGE(R.string.message, Icons.Default.Message, onValueChange =  { })
}

