package hhhcosmetics.luggage.hhhtravellink.ui.composable.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import hhhcosmetics.luggage.hhhtravellink.R
import hhhcosmetics.luggage.hhhtravellink.ui.viewmodel.QJIOOOnboardingVM
import org.koin.androidx.compose.koinViewModel

private data class Page(val title: String, val description: String, val image: Int)

private val pages = listOf(
    Page("Pack with intention", "Choose luggage designed to make every departure feel effortless.", R.drawable.onboarding_1),
    Page("Find your travel rhythm", "Compare cabin cases, backpacks, organizers, and comfort essentials in one curated collection.", R.drawable.onboarding_2),
    Page("Reserve, then collect", "Book your favourites and we will hold your order in store for the next 24 hours.", R.drawable.onboarding_3),
)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: QJIOOOnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
) {
    val saved by viewModel.onboardingSetState.collectAsState()
    val pagerState = rememberPagerState(pageCount = { pages.size })

    LaunchedEffect(saved) {
        if (saved) {
            onNavigateToHomeScreen()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f),
        ) { index ->
            val page = pages[index]
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(page.image),
                    contentDescription = page.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .height(430.dp),
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.94f))
                        .padding(28.dp),
                ) {
                    Text(page.title, style = MaterialTheme.typography.displayLarge)
                    Text(
                        page.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 12.dp),
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                pages.indices.forEach { index ->
                    Box(
                        Modifier
                            .size(if (pagerState.currentPage == index) 10.dp else 7.dp)
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
            if (pagerState.currentPage == pages.lastIndex) {
                Button(
                    onClick = viewModel::setOnboarded,
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Text("Get Started")
                }
            }
        }
    }
}
