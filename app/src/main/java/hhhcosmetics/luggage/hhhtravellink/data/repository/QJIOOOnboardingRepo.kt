package hhhcosmetics.luggage.hhhtravellink.data.repository

import hhhcosmetics.luggage.hhhtravellink.data.datastore.QJIOOOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class QJIOOOnboardingRepo(
    private val qjiooOnboardingStoreManager: QJIOOOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return qjiooOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            qjiooOnboardingStoreManager.setOnboardedState(state)
        }
    }
}