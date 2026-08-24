package hhhcosmetics.luggage.hhhtravellink.ui.composable.screen.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Luggage
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import hhhcosmetics.luggage.hhhtravellink.ui.composable.shared.QJIOOContentWrapper
import hhhcosmetics.luggage.hhhtravellink.ui.state.CartItemUiState
import hhhcosmetics.luggage.hhhtravellink.ui.state.DataUiState
import hhhcosmetics.luggage.hhhtravellink.ui.viewmodel.CartViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit,
) {
    val state by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val total by viewModel.totalPrice.collectAsStateWithLifecycle()
    QJIOOContentWrapper(
        dataState = state,
        dataPopulated = {
            CartContent(
                items = (state as DataUiState.Populated).data,
                total = total,
                modifier = modifier,
                onPlus = viewModel::incrementProductInCart,
                onMinus = { item ->
                    if (item.quantity == 1) {
                        viewModel.deleteFromCart(item.productId)
                    } else {
                        viewModel.decrementItemInCart(item.productId)
                    }
                },
                onDelete = viewModel::deleteFromCart,
                onCheckout = onNavigateToCheckoutScreen,
            )
        },
        dataEmpty = {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(Icons.Default.Luggage, contentDescription = null, modifier = Modifier.size(72.dp), tint = MaterialTheme.colorScheme.primary)
                Text("Your next journey starts here", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 18.dp))
                Text("Start Shopping", color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(top = 8.dp))
            }
        },
    )
}

@Composable
private fun CartContent(
    items: List<CartItemUiState>,
    total: Double,
    modifier: Modifier,
    onPlus: (Int) -> Unit,
    onMinus: (CartItemUiState) -> Unit,
    onDelete: (Int) -> Unit,
    onCheckout: () -> Unit,
) {
    Column(modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(items, key = { it.productId }) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AsyncImage(
                        model = item.productImageUrl,
                        contentDescription = item.productTitle,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(72.dp)
                            .clip(RoundedCornerShape(4.dp)),
                    )
                    Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                        Text(item.productTitle, style = MaterialTheme.typography.titleMedium)
                        Text("£%.2f".format(item.productPrice), color = MaterialTheme.colorScheme.primary)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = { onMinus(item) }) {
                                Text("−", style = MaterialTheme.typography.titleLarge)
                            }
                            Text(item.quantity.toString())
                            IconButton(onClick = { onPlus(item.productId) }) {
                                Text("+", style = MaterialTheme.typography.titleLarge)
                            }
                        }
                    }
                    IconButton(onClick = { onDelete(item.productId) }) {
                        Text("×", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(18.dp),
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total", style = MaterialTheme.typography.titleLarge)
                Text("£%.2f".format(total), style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
            }
            Button(onClick = onCheckout, modifier = Modifier.fillMaxWidth().padding(top = 14.dp)) {
                Text("Proceed to Checkout")
            }
        }
    }
}
