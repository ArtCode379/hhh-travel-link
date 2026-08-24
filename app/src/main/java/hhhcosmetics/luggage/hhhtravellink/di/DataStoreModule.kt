package hhhcosmetics.luggage.hhhtravellink.di

import hhhcosmetics.luggage.hhhtravellink.data.datastore.QJIOOOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { QJIOOOnboardingPrefs(androidContext()) }
}