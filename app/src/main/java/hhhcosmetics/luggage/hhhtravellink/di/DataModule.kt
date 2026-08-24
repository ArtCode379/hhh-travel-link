package hhhcosmetics.luggage.hhhtravellink.di

import hhhcosmetics.luggage.hhhtravellink.data.repository.CartRepository
import hhhcosmetics.luggage.hhhtravellink.data.repository.QJIOOOnboardingRepo
import hhhcosmetics.luggage.hhhtravellink.data.repository.OrderRepository
import hhhcosmetics.luggage.hhhtravellink.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        QJIOOOnboardingRepo(
            qjiooOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}