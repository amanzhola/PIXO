package com.company.pixo.data.url

class BackendUrlResolver(
    private val backendBaseUrl: String
) {

    fun resolve(url: String?): String? {
        if (url.isNullOrBlank()) {
            return null
        }

        val fixedUrl = url.replace("/uploads/input/", "/uploads/")

        if (fixedUrl.startsWith("http://") || fixedUrl.startsWith("https://")) {
            return fixedUrl
        }

        val normalizedPath = if (fixedUrl.startsWith("/")) {
            fixedUrl
        } else {
            "/$fixedUrl"
        }

        return backendBaseUrl.trimEnd('/') + normalizedPath
    }
}