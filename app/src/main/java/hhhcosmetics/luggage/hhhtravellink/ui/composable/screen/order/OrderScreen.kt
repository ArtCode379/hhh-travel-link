package hhhcosmetics.luggage.hhhtravellink.ui.composable.screen.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import hhhcosmetics.luggage.hhhtravellink.R
import hhhcosmetics.luggage.hhhtravellink.data.entity.OrderEntity
import hhhcosmetics.luggage.hhhtravellink.ui.composable.shared.QJIOOContentWrapper
import hhhcosmetics.luggage.hhhtravellink.ui.composable.shared.QJIOOEmptyView
import hhhcosmetics.luggage.hhhtravellink.ui.state.DataUiState
import hhhcosmetics.luggage.hhhtravellink.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = koinViewModel(),
) {
    val ordersState by viewModel.ordersState.collectAsState()

    OrdersContent(
        ordersState = ordersState,
        modifier = modifier,
    )
}

@Composable
private fun OrdersContent(
    ordersState: DataUiState<List<OrderEntity>>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {

        QJIOOContentWrapper(
            dataState = ordersState,

            dataPopulated = {
                val data = (ordersState as DataUiState.Populated).data

            },

            dataEmpty = {
                QJIOOEmptyView(
                    primaryText = stringResource(R.string.qjioo_orders_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}