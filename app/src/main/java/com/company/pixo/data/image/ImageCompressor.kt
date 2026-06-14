package com.company.pixo.data.image

interface ImageCompressor {

    fun compressToJpegBytes(
        localImageUri: String
    ): ByteArray
}