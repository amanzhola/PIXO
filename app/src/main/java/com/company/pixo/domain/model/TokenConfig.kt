package com.company.pixo.domain.model

object TokenConfig {

    const val INITIAL_BALANCE = 10

    val PACKAGES: List<TokenPackage> = listOf(
        TokenPackage(
            id = "tokens_100",
            amount = 100,
            priceText = "$9.99"
        ),
        TokenPackage(
            id = "tokens_500",
            amount = 500,
            priceText = "$34.99"
        ),
        TokenPackage(
            id = "tokens_1000",
            amount = 1000,
            priceText = "$59.99"
        ),
        TokenPackage(
            id = "tokens_2000",
            amount = 2000,
            priceText = "$99.99"
        )
    )

    fun resolveOutputCount(
        config: ToolConfig,
        prompt: String?
    ): Int {
        val isCartoonPrompt = prompt
            ?.contains(
                other = "cartoon",
                ignoreCase = true
            ) == true

        return if (
            isCartoonPrompt &&
            config.generation.cartoonOutputCount != null
        ) {
            config.generation.cartoonOutputCount
        } else {
            config.generation.defaultOutputCount
        }
    }

    fun resolveTokenCost(
        config: ToolConfig
    ): Int {
        return config.generation.tokenCost
    }
}