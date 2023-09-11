package com.dafinrs.tixcompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.dafinrs.tixcompose.R
import com.dafinrs.tixcompose.presentations.sign.ErrorForm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldCompose(
    modifier: Modifier = Modifier,
    value: String,
    isError: Boolean = false,
    errorForm: ErrorForm? = null,
    onClearText:() -> Unit,
    onChangeValue: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        placeholder = {
            Text(
                text = stringResource(id = R.string.api_key_text_field),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        },
        isError = isError,
        maxLines = 1,
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable {
                    onClearText()
                },
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_cancel_24),
                contentDescription = stringResource(id = R.string.clean_text)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        supportingText = {
            when (errorForm) {
                ErrorForm.EMPTY -> {
                    Text(
                        text = stringResource(id = ErrorForm.EMPTY.errorDesc),
                        color = MaterialTheme.colorScheme.error
                    )
                }

                else -> Unit
            }
        },
        onValueChange = {
            onChangeValue(it)
        },
    )
}