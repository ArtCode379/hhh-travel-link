package hhhcosmetics.luggage.hhhtravellink.ui.composable.screen.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hhhcosmetics.luggage.hhhtravellink.data.entity.OrderEntity
import hhhcosmetics.luggage.hhhtravellink.ui.composable.shared.QJIOOContentWrapper
import hhhcosmetics.luggage.hhhtravellink.ui.state.DataUiState
import hhhcosmetics.luggage.hhhtravellink.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.format.DateTimeFormatter

@Composable
fun OrdersScreen(modifier: Modifier = Modifier, viewModel: OrderViewModel = koinViewModel()) {
    val state by viewModel.ordersState.collectAsState()
    QJIOOContentWrapper(
        dataState = state,
        dataPopulated = {
            val orders = (state as DataUiState.Populated).data.sortedByDescending { it.timestamp }
            LazyColumn(modifier = modifier.fillMaxSize(), contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp)) {
                items(orders, key = { it.orderNumber }) { order ->
                    OrderCard(order)
                }
            }
        },
        dataEmpty = {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No orders yet", style = MaterialTheme.typography.titleLarge)
            }
        },
    )
}

@Composable
private fun OrderCard(order: OrderEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp)
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(6.dp))
            .padding(16.dp),
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Order #${order.orderNumber}", style = MaterialTheme.typography.titleMedium)
            Text("Reserved", color = MaterialTheme.colorScheme.tertiary, style = MaterialTheme.typography.labelLarge)
        }
        Text(order.timestamp.format(DateTimeFormatter.ofPattern("dd MMM yyyy")), color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(order.description, modifier = Modifier.padding(top = 10.dp), style = MaterialTheme.typography.bodyMedium)
        Text("£%.2f".format(order.price), modifier = Modifier.padding(top = 10.dp), style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        Text("Awaiting collection in store for 24 hours", modifier = Modifier.padding(top = 8.dp), color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
