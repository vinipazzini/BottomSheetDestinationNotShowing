package com.example.missingcomposable.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import com.example.missingcomposable.R
import com.example.missingcomposable.theme.Spacing

@Suppress("ReusedModifierInstance")
@Composable
fun OutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: String? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    helperMessage: String? = null,
    counterMessage: String? = null,
    errorMessage: String? = null,
    isError: Boolean = errorMessage != null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    passwordToggleEnabled: Boolean = false,
    keyboardOptions: KeyboardOptions = if (passwordToggleEnabled) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = TextFieldDefaults.outlinedShape,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
) {
    var passwordVisibility: Boolean by remember { mutableStateOf(!passwordToggleEnabled) }
    val passwordToggleVisualTransformation = if (passwordToggleEnabled) {
        if (passwordVisibility) visualTransformation else PasswordVisualTransformation()
    } else {
        visualTransformation
    }

    val passwordToggleIcon = if (passwordVisibility) {
        painterResource(id = R.drawable.ui_ic_visibility)
    } else {
        painterResource(id = R.drawable.ui_ic_visibility_off)
    }

    val textFieldTrailingIcon: @Composable (() -> Unit) = {
        when {
            isError -> Icon(painter = painterResource(id = R.drawable.ui_ic_error), null)
            passwordToggleEnabled -> IconButton(
                onClick = {
                    passwordVisibility = !passwordVisibility
                },
            ) {
                Icon(passwordToggleIcon, null)
            }
            else -> trailingIcon?.invoke()
        }
    }
    ConstraintLayout {
        val (textFieldRef, helperMessageRef, counterMessageRef) = createRefs()
        androidx.compose.material3.OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier.constrainAs(textFieldRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            label = { label?.let { Text(text = it) } },
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = textFieldTrailingIcon,
            isError = isError,
            visualTransformation = passwordToggleVisualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            interactionSource = interactionSource,
            shape = shape,
            colors = colors,
        )
        Text(
            text = if (isError && errorMessage != null) errorMessage else helperMessage.orEmpty(),
            color = if (isError && errorMessage != null) MaterialTheme.colorScheme.error else Color.Unspecified,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .constrainAs(helperMessageRef) {
                    top.linkTo(textFieldRef.bottom)
                    start.linkTo(textFieldRef.start, Spacing.x02)
                    end.linkTo(counterMessageRef.start, Spacing.x01, goneMargin = 12.dp)
                    width = Dimension.fillToConstraints
                    visibility =
                        goneIf { if (isError) errorMessage == null else helperMessage == null }
                }
                .defaultMinSize(minHeight = 16.dp),
        )
        Text(
            text = counterMessage.orEmpty(),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .constrainAs(counterMessageRef) {
                    top.linkTo(textFieldRef.bottom)
                    end.linkTo(textFieldRef.end, 12.dp)
                    width = Dimension.fillToConstraints
                    visibility = goneIf { counterMessage == null }
                }
                .defaultMinSize(minHeight = 16.dp),
        )
    }
}


fun goneIf(block: () -> Boolean) = if (block()) Visibility.Gone else Visibility.Visible
