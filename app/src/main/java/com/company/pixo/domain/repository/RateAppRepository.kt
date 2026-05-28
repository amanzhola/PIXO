package com.company.pixo.domain.repository

import android.app.Activity

interface RateAppRepository {

    suspend fun requestReview(
        activity: Activity
    ): Boolean
}