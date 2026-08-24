package hhhcosmetics.luggage.hhhtravellink.ui.composable.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import hhhcosmetics.luggage.hhhtravellink.R
import hhhcosmetics.luggage.hhhtravellink.data.model.Product
import hhhcosmetics.luggage.hhhtravellink.ui.composable.shared.QJIOOContentWrapper
import hhhcosmetics.luggage.hhhtravellink.ui.composable.shared.QJIOOEmptyView
import hhhcosmetics.luggage.hhhtravellink.ui.state.DataUiState
import hhhcosmetics.luggage.hhhtravellink.ui.viewmodel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val productsState by viewModel.productsState.collectAsState()

    HomeContent(
        productsState = productsState,
        modifier = modifier,
        onNavigateToProductDetails = onNavigateToProductDetails,
        onAddProductToCart = viewModel::addToCart,
    )
}

@Composable
private fun HomeContent(
    productsState: DataUiState<List<Product>>,
    modifier: Modifier = Modifier,
    onNavigateToProductDetails: (productId: Int) -> Unit,
    onAddProductToCart: (productId: Int) -> Unit,
) {
    Column(modifier = modifier) {

        QJIOOContentWrapper(
            dataState = productsState,

            dataPopulated = {
                val data = (productsState as DataUiState.Populated).data
            },

            dataEmpty = {
                QJIOOEmptyView(
                    primaryText = stringResource(R.string.qjioo_products_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}