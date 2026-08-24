package hhhcosmetics.luggage.hhhtravellink.ui.composable.screen.checkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hhhcosmetics.luggage.hhhtravellink.ui.state.DataUiState
import hhhcosmetics.luggage.hhhtravellink.ui.viewmodel.CheckoutViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = koinViewModel(),
    onNavigateToOrdersScreen: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val orderState by viewModel.orderState.collectAsStateWithLifecycle()
    val emailInvalid by viewModel.emailInvalidState.collectAsStateWithLifecycle()
    val enabled by remember {
        derivedStateOf {
            viewModel.customerFirstName.isNotBlank() &&
                viewModel.customerLastName.isNotBlank() &&
                viewModel.customerEmail.isNotBlank()
        }
    }
    if (orderState is DataUiState.Populated) {
        CheckoutDialog(onConfirm = onNavigateToOrdersScreen)
    }
    CheckoutContent(
        name = viewModel.customerFirstName,
        address = viewModel.customerLastName,
        contact = viewModel.customerEmail,
        contactInvalid = emailInvalid,
        modifier = modifier,
        focusManager = focusManager,
        enabled = enabled,
        onNameChanged = viewModel::updateCustomerFirstName,
        onAddressChanged = viewModel::updateCustomerLastName,
        onContactChanged = viewModel::updateCustomerEmail,
        onPlaceOrder = viewModel::placeOrder,
    )
}

@Composable
private fun CheckoutContent(
    name: String,
    address: String,
    contact: String,
    contactInvalid: Boolean,
    modifier: Modifier,
    focusManager: FocusManager,
    enabled: Boolean,
    onNameChanged: (String) -> Unit,
    onAddressChanged: (String) -> Unit,
    onContactChanged: (String) -> Unit,
    onPlaceOrder: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text("Reserve your order", style = MaterialTheme.typography.headlineMedium)
        Text("Enter your collection details. Your order will be held in store for 24 hours.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        CheckoutTextField(name, onNameChanged, "Full name", Modifier.fillMaxWidth())
        CheckoutTextField(address, onAddressChanged, "Address", Modifier.fillMaxWidth())
        CheckoutTextField(
            input = contact,
            onInputChange = onContactChanged,
            labelText = "Contact email",
            modifier = Modifier.fillMaxWidth(),
            isError = contactInvalid,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        )
        if (contactInvalid) {
            Text("Enter a valid email address", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium)
        }
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Collection reservation", style = MaterialTheme.typography.titleMedium)
                Text("Your items and total are confirmed from the cart. No payment is taken in the app.", modifier = Modifier.padding(top = 8.dp))
            }
        }
        Button(onClick = onPlaceOrder, enabled = enabled, modifier = Modifier.fillMaxWidth()) {
            Text("Place Order")
        }
    }
}

@Composable
fun CheckoutTextField(
    input: String,
    onInputChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    OutlinedTextField(
        value = input,
        onValueChange = onInputChange,
        label = { Text(labelText) },
        modifier = modifier,
        enabled = enabled,
        isError = isError,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
    )
}
