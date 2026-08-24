package hhhcosmetics.luggage.hhhtravellink.di

import hhhcosmetics.luggage.hhhtravellink.ui.viewmodel.AppViewModel
import hhhcosmetics.luggage.hhhtravellink.ui.viewmodel.CartViewModel
import hhhcosmetics.luggage.hhhtravellink.ui.viewmodel.CheckoutViewModel
import hhhcosmetics.luggage.hhhtravellink.ui.viewmodel.QJIOOOnboardingVM
import hhhcosmetics.luggage.hhhtravellink.ui.viewmodel.OrderViewModel
import hhhcosmetics.luggage.hhhtravellink.ui.viewmodel.ProductDetailsViewModel
import hhhcosmetics.luggage.hhhtravellink.ui.viewmodel.ProductViewModel
import hhhcosmetics.luggage.hhhtravellink.ui.viewmodel.QJIOOSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        QJIOOSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        QJIOOOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}