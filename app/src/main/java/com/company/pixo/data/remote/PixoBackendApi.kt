package com.company.pixo.data.remote

import com.company.pixo.data.remote.dto.GenerationCreateDto
import com.company.pixo.data.remote.dto.GenerationCreateDtoResult
import com.company.pixo.data.remote.dto.GenerationResultDto
import com.company.pixo.data.remote.dto.GenerationStatusDto
import com.company.pixo.data.remote.dto.HealthDto
import com.company.pixo.data.remote.dto.ImageUploadDto
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PixoBackendApi {

    @GET("health")
    suspend fun health(): HealthDto

    @POST("images/upload")
    suspend fun uploadImage(
        @Body body: RequestBody
    ): ImageUploadDto

    @POST("generations")
    suspend fun createGeneration(
        @Body request: GenerationCreateDto
    ): GenerationCreateDtoResult

    @GET("generations/{taskId}")
    suspend fun getGeneration(
        @Path("taskId") taskId: String
    ): GenerationStatusDto

    @GET("generations/{taskId}/result")
    suspend fun getResult(
        @Path("taskId") taskId: String
    ): GenerationResultDto
}