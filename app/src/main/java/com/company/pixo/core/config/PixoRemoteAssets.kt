package com.company.pixo.core.config

object PixoRemoteAssets {
    private const val BASE =
        "https://raw.githubusercontent.com/amanzhola/PIXO/feature/onboarding-assets-backend/server-assets/assets"

    const val ONBOARDING = "$BASE/onboarding"

    const val TOOLS = "$BASE/tools"
    const val TOOLS_HEADER = "$BASE/tools/header"

    const val TEMPLATES = "$BASE/templates"

    const val PROMPT = "$BASE/prompt"

    const val TOKENS = "$BASE/tokens"

    const val PAYWALL = "$BASE/paywall"
    const val PAYWALL_VERSIONS = "$BASE/paywall/versions"

    const val BOTTOMSHEET_ALBUMS = "$BASE/bottomsheet/albums"
    const val BOTTOMSHEET_PHOTO_REQUIREMENTS = "$BASE/bottomsheet/photo-requirements"

    const val CAMERA = "$BASE/camera"

    const val SMILE_EDIT = "$BASE/smile_edit"
}