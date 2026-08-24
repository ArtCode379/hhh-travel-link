package hhhcosmetics.luggage.hhhtravellink.ui.composable.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import hhhcosmetics.luggage.hhhtravellink.R
import hhhcosmetics.luggage.hhhtravellink.data.model.Product
import hhhcosmetics.luggage.hhhtravellink.data.model.ProductCategory
import hhhcosmetics.luggage.hhhtravellink.ui.composable.shared.QJIOOContentWrapper
import hhhcosmetics.luggage.hhhtravellink.ui.state.DataUiState
import hhhcosmetics.luggage.hhhtravellink.ui.viewmodel.ProductViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val state by viewModel.productsState.collectAsState()
    QJIOOContentWrapper(
        dataState = state,
        dataPopulated = {
            ProductCatalog(
                products = (state as DataUiState.Populated).data,
                modifier = modifier,
                onProductClick = onNavigateToProductDetails,
            )
        },
        dataEmpty = {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(stringResource(R.string.qjioo_products_state_empty_primary_text))
            }
        },
    )
}

@Composable
private fun ProductCatalog(
    products: List<Product>,
    modifier: Modifier,
    onProductClick: (Int) -> Unit,
) {
    var selectedCategory by remember { mutableStateOf<ProductCategory?>(null) }
    val featured = products.take(4)
    val pagerState = rememberPagerState(pageCount = { featured.size })
    val filtered = selectedCategory?.let { category -> products.filter { it.category == category } } ?: products

    LaunchedEffect(pagerState.currentPage) {
        delay(4000)
        pagerState.animateScrollToPage((pagerState.currentPage + 1) % featured.size)
    }

    Column(modifier = modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
        ) { page ->
            HeroCard(featured[page], onProductClick)
        }
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            featured.indices.forEach { index ->
                Box(
                    Modifier
                        .size(if (pagerState.currentPage == index) 8.dp else 6.dp)
                        .clip(CircleShape)
                        .background(
                            if (pagerState.currentPage == index) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.outline
                            }
                        )
                )
            }
        }
        Text(
            text = "Travel, beautifully considered",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(start = 18.dp, top = 20.dp, end = 18.dp),
        )
        LazyRow(
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 12.dp),
        ) {
            item {
                FilterChip(
                    selected = selectedCategory == null,
                    onClick = { selectedCategory = null },
                    label = { Text("All") },
                )
            }
            items(ProductCategory.entries.size) { index ->
                val category = ProductCategory.entries[index]
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category },
                    label = { Text(stringResource(category.titleRes)) },
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(filtered, key = { it.id }) { product ->
                ProductCard(product, onProductClick)
            }
        }
    }
}

@Composable
private fun HeroCard(product: Product, onProductClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onProductClick(product.id) },
    ) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Box(
            Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(androidx.compose.ui.graphics.Color.Transparent, androidx.compose.ui.graphics.Color(0xCC1A0E18))))
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp),
        ) {
            Text(product.title, color = androidx.compose.ui.graphics.Color.White, style = MaterialTheme.typography.headlineMedium)
            Text("£%.2f".format(product.price), color = androidx.compose.ui.graphics.Color.White, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
private fun ProductCard(product: Product, onProductClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onProductClick(product.id) },
    ) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(if (product.id % 2 == 0) 0.82f else 1f),
        )
        Column(modifier = Modifier.padding(12.dp)) {
            Text(stringResource(product.category.titleRes).uppercase(), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary)
            Text(product.title, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 5.dp))
            Text("£%.2f".format(product.price), style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(top = 7.dp))
        }
    }
}
