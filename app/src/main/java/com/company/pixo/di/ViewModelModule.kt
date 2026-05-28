package com.company.pixo.di

import com.company.pixo.feature.paywall.PaywallViewModel
import com.company.pixo.feature.prompt.PromptViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        PaywallViewModel(
            subscriptionRepository = get()
        )
    }

    viewModel {
        PromptViewModel()
    }
}