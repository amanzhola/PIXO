package com.company.pixo.data.repository

import android.app.Activity
import android.util.Log
import com.company.pixo.domain.repository.RateAppRepository
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.review.testing.FakeReviewManager
import kotlinx.coroutines.tasks.await

private const val USE_FAKE_REVIEW_MANAGER = true

class PlayRateAppRepository : RateAppRepository {

    override suspend fun requestReview(
        activity: Activity
    ): Boolean {
        return try {
            val manager = if (USE_FAKE_REVIEW_MANAGER) {
                FakeReviewManager(activity)
            } else {
                ReviewManagerFactory.create(activity)
            }

            val reviewInfo = manager
                .requestReviewFlow()
                .await()

            manager
                .launchReviewFlow(activity, reviewInfo)
                .await()

            true
        } catch (throwable: Throwable) {
            Log.e("PlayRateAppRepository", "Review flow failed", throwable)
            false
        }
    }
}