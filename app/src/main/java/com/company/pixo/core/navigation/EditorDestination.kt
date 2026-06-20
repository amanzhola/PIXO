package com.company.pixo.core.navigation

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.company.pixo.R
import com.company.pixo.domain.model.GenerationCreateRequest
import com.company.pixo.domain.model.PixoToolConfigs
import com.company.pixo.domain.model.ToolType
import com.company.pixo.feature.editor.PixoAiEnhancerEditRoute
import com.company.pixo.feature.editor.PixoGhibliEditRoute
import com.company.pixo.feature.editor.PixoGhostfaceEditRoute
import com.company.pixo.feature.editor.PixoGlamMakeupEditRoute
import com.company.pixo.feature.editor.PixoHairStyleEditRoute
import com.company.pixo.feature.editor.PixoRemoveBackgroundEditRoute
import com.company.pixo.feature.editor.PixoSkinImproveEditRoute
import com.company.pixo.feature.editor.PixoSmileEditRoute
import com.company.pixo.feature.editor.PixoUpscaleImageEditRoute
import com.company.pixo.feature.editor.component.PixoSinglePromptEditRoute
import com.company.pixo.feature.main.MainTab

fun NavGraphBuilder.editorDestination(
    navController: NavHostController,
    actions: AppNavigationActions
) {
    composable(
        route = AppRoute.Editor.route,
        arguments = listOf(
            navArgument("toolType") {
                type = NavType.StringType
            },
            navArgument("imageUri") {
                type = NavType.StringType
                nullable = true
                defaultValue = ""
            }
        )
    ) { backStackEntry ->
        val toolType = backStackEntry.arguments
            ?.getString("toolType")
            ?.let { value ->
                runCatching {
                    ToolType.valueOf(value)
                }.getOrNull()
            }

        val imageUri = backStackEntry.arguments
            ?.getString("imageUri")
            .orEmpty()

        fun navigateBackToTools() {
            navController.navigate(
                AppRoute.Main.createRoute(MainTab.Tools)
            ) {
                popUpTo(AppRoute.Main.route) {
                    inclusive = false
                }
                launchSingleTop = true
            }
        }

        when (toolType) {
            ToolType.AI_ENHANCER -> {
                PixoAiEnhancerEditRoute(
                    imageUri = imageUri,
                    onBackClick = {
                        navigateBackToTools()
                    },
                    onGenerateClick = { selectedOption, _ ->
                        val config = PixoToolConfigs.findByType(ToolType.AI_ENHANCER)
                            ?: return@PixoAiEnhancerEditRoute

                        val request = GenerationCreateRequest(
                            toolType = config.type,
                            backendType = config.backendType,
                            serverAction = config.serverAction,
                            sourceImageUrl = null,
                            sourceImageUri = imageUri,
                            prompt = null,
                            templateId = null,
                            options = mapOf(
                                "enhanceMode" to selectedOption.serverValue
                            ),
                            tokenCost = config.generation.tokenCost,
                            outputCount = config.generation.defaultOutputCount,
                            historyIdentity = config.historyIdentity
                        )

                        actions.createGenerationWithValidation(
                            request = request,
                            requiredFieldsValid = imageUri.isNotBlank()
                        )
                    }
                )
            }

            ToolType.GLAM_MAKEUP -> {
                PixoGlamMakeupEditRoute(
                    imageUri = imageUri,
                    onBackClick = {
                        navigateBackToTools()
                    },
                    onGenerateClick = { selectedStyle, optionalDetails ->
                        val config = PixoToolConfigs.findByType(ToolType.GLAM_MAKEUP)

                        if (config != null) {
                            val request = GenerationCreateRequest(
                                toolType = config.type,
                                backendType = config.backendType,
                                serverAction = config.serverAction,
                                sourceImageUrl = null,
                                sourceImageUri = imageUri,
                                prompt = optionalDetails,
                                templateId = null,
                                options = mapOf(
                                    "makeupStyle" to selectedStyle.serverValue
                                ),
                                tokenCost = config.generation.tokenCost,
                                outputCount = config.generation.defaultOutputCount,
                                historyIdentity = config.historyIdentity
                            )

                            actions.createGenerationWithValidation(
                                request = request,
                                requiredFieldsValid = imageUri.isNotBlank()
                            )
                        }
                    }
                )
            }

            ToolType.REMOVE_OBJECTS -> {
                PixoSinglePromptEditRoute(
                    imageUri = imageUri,
                    titleRes = R.string.tool_remove_objects,
                    fieldTitle = stringResource(R.string.remove_objects_style_title),
                    fieldHint = stringResource(R.string.remove_objects_style_hint),
                    onBackClick = {
                        navigateBackToTools()
                    },
                    onGenerateClick = { value ->
                        val config = PixoToolConfigs.findByType(ToolType.REMOVE_OBJECTS)
                            ?: return@PixoSinglePromptEditRoute

                        val request = GenerationCreateRequest(
                            toolType = config.type,
                            backendType = config.backendType,
                            serverAction = config.serverAction,
                            sourceImageUrl = null,
                            sourceImageUri = imageUri,
                            prompt = value,
                            templateId = null,
                            options = emptyMap(),
                            tokenCost = config.generation.tokenCost,
                            outputCount = config.generation.defaultOutputCount,
                            historyIdentity = config.historyIdentity
                        )

                        actions.createGenerationWithValidation(
                            request = request,
                            requiredFieldsValid = imageUri.isNotBlank() && value.isNotBlank()
                        )
                    }
                )
            }

            ToolType.REMOVE_BACKGROUND -> {
                PixoRemoveBackgroundEditRoute(
                    imageUri = imageUri,
                    onBackClick = {
                        navigateBackToTools()
                    },
                    onGenerateClick = { backgroundType ->
                        val config = PixoToolConfigs.findByType(ToolType.REMOVE_BACKGROUND)
                            ?: return@PixoRemoveBackgroundEditRoute

                        val request = GenerationCreateRequest(
                            toolType = config.type,
                            backendType = config.backendType,
                            serverAction = config.serverAction,
                            sourceImageUrl = null,
                            sourceImageUri = imageUri,
                            prompt = config.defaultPrompt,
                            templateId = null,
                            options = mapOf(
                                "backgroundType" to backgroundType
                            ),
                            tokenCost = config.generation.tokenCost,
                            outputCount = config.generation.defaultOutputCount,
                            historyIdentity = config.historyIdentity
                        )

                        actions.createGenerationWithValidation(
                            request = request,
                            requiredFieldsValid = imageUri.isNotBlank()
                        )
                    }
                )
            }

            ToolType.SKIN_IMPROVE -> {
                PixoSkinImproveEditRoute(
                    imageUri = imageUri,
                    onBackClick = {
                        navigateBackToTools()
                    },
                    onGenerateClick = {
                        val config = PixoToolConfigs.findByType(ToolType.SKIN_IMPROVE)
                            ?: return@PixoSkinImproveEditRoute

                        val request = GenerationCreateRequest(
                            toolType = config.type,
                            backendType = config.backendType,
                            serverAction = config.serverAction,
                            sourceImageUrl = null,
                            sourceImageUri = imageUri,
                            prompt = config.defaultPrompt,
                            templateId = null,
                            options = emptyMap(),
                            tokenCost = config.generation.tokenCost,
                            outputCount = config.generation.defaultOutputCount,
                            historyIdentity = config.historyIdentity
                        )

                        actions.createGenerationWithValidation(
                            request = request,
                            requiredFieldsValid = imageUri.isNotBlank()
                        )
                    }
                )
            }

            ToolType.UPSCALE_IMAGE -> {
                PixoUpscaleImageEditRoute(
                    imageUri = imageUri,
                    onBackClick = {
                        navigateBackToTools()
                    },
                    onGenerateClick = {
                        val config = PixoToolConfigs.findByType(ToolType.UPSCALE_IMAGE)
                            ?: return@PixoUpscaleImageEditRoute

                        val request = GenerationCreateRequest(
                            toolType = config.type,
                            backendType = config.backendType,
                            serverAction = config.serverAction,
                            sourceImageUrl = null,
                            sourceImageUri = imageUri,
                            prompt = config.defaultPrompt,
                            templateId = null,
                            options = emptyMap(),
                            tokenCost = config.generation.tokenCost,
                            outputCount = config.generation.defaultOutputCount,
                            historyIdentity = config.historyIdentity
                        )

                        actions.createGenerationWithValidation(
                            request = request,
                            requiredFieldsValid = imageUri.isNotBlank()
                        )
                    }
                )
            }

            ToolType.CHANGE_SCENE -> {
                PixoSinglePromptEditRoute(
                    imageUri = imageUri,
                    titleRes = R.string.tool_change_scene,
                    fieldTitle = stringResource(R.string.change_scene_describe_title),
                    fieldHint = stringResource(R.string.change_scene_hint),
                    onBackClick = {
                        navigateBackToTools()
                    },
                    onGenerateClick = { value ->
                        val config = PixoToolConfigs.findByType(ToolType.CHANGE_SCENE)
                            ?: return@PixoSinglePromptEditRoute

                        val request = GenerationCreateRequest(
                            toolType = config.type,
                            backendType = config.backendType,
                            serverAction = config.serverAction,
                            sourceImageUrl = null,
                            sourceImageUri = imageUri,
                            prompt = value,
                            templateId = null,
                            options = emptyMap(),
                            tokenCost = config.generation.tokenCost,
                            outputCount = config.generation.defaultOutputCount,
                            historyIdentity = config.historyIdentity
                        )

                        actions.createGenerationWithValidation(
                            request = request,
                            requiredFieldsValid = imageUri.isNotBlank() && value.isNotBlank()
                        )
                    }
                )
            }

            ToolType.HAIR_STUDIO -> {
                PixoHairStyleEditRoute(
                    imageUri = imageUri,
                    onBackClick = {
                        navigateBackToTools()
                    },
                    onGenerateClick = { hairstyle, length, color ->
                        val config = PixoToolConfigs.findByType(ToolType.HAIR_STUDIO)
                            ?: return@PixoHairStyleEditRoute

                        val request = GenerationCreateRequest(
                            toolType = config.type,
                            backendType = config.backendType,
                            serverAction = config.serverAction,
                            sourceImageUrl = null,
                            sourceImageUri = imageUri,
                            prompt = config.defaultPrompt,
                            templateId = null,
                            options = buildMap {
                                if (hairstyle.isNotBlank()) {
                                    put("hairstyle", hairstyle)
                                }

                                if (length.isNotBlank()) {
                                    put("length", length)
                                }

                                if (color.isNotBlank()) {
                                    put("color", color)
                                }
                            },
                            tokenCost = config.generation.tokenCost,
                            outputCount = config.generation.defaultOutputCount,
                            historyIdentity = config.historyIdentity
                        )

                        actions.createGenerationWithValidation(
                            request = request,
                            requiredFieldsValid = imageUri.isNotBlank() &&
                                    (
                                            hairstyle.isNotBlank() ||
                                                    length.isNotBlank() ||
                                                    color.isNotBlank()
                                            )
                        )
                    }
                )
            }

            ToolType.SMILE_EDIT -> {
                PixoSmileEditRoute(
                    imageUri = imageUri,
                    onBackClick = {
                        navigateBackToTools()
                    },
                    onGenerateClick = { smileLevel ->
                        val config = PixoToolConfigs.findByType(ToolType.SMILE_EDIT)
                            ?: return@PixoSmileEditRoute

                        val request = GenerationCreateRequest(
                            toolType = config.type,
                            backendType = config.backendType,
                            serverAction = config.serverAction,
                            sourceImageUrl = null,
                            sourceImageUri = imageUri,
                            prompt = config.defaultPrompt,
                            templateId = null,
                            options = mapOf(
                                "smileLevel" to smileLevel
                            ),
                            tokenCost = config.generation.tokenCost,
                            outputCount = config.generation.defaultOutputCount,
                            historyIdentity = config.historyIdentity
                        )

                        actions.createGenerationWithValidation(
                            request = request,
                            requiredFieldsValid = imageUri.isNotBlank()
                        )
                    }
                )
            }

            ToolType.GHOSTFACE -> {
                PixoGhostfaceEditRoute(
                    imageUri = imageUri,
                    onBackClick = {
                        navigateBackToTools()
                    },
                    onGenerateClick = {
                        val config = PixoToolConfigs.findByType(ToolType.GHOSTFACE)
                            ?: return@PixoGhostfaceEditRoute

                        val request = GenerationCreateRequest(
                            toolType = config.type,
                            backendType = config.backendType,
                            serverAction = config.serverAction,
                            sourceImageUrl = null,
                            sourceImageUri = imageUri,
                            prompt = config.defaultPrompt,
                            templateId = null,
                            options = emptyMap(),
                            tokenCost = config.generation.tokenCost,
                            outputCount = config.generation.defaultOutputCount,
                            historyIdentity = config.historyIdentity
                        )

                        actions.createGenerationWithValidation(
                            request = request,
                            requiredFieldsValid = imageUri.isNotBlank()
                        )
                    }
                )
            }

            ToolType.GHIBLI -> {
                PixoGhibliEditRoute(
                    imageUri = imageUri,
                    onBackClick = {
                        navigateBackToTools()
                    },
                    onGenerateClick = {
                        val config = PixoToolConfigs.findByType(ToolType.GHIBLI)
                            ?: return@PixoGhibliEditRoute

                        val request = GenerationCreateRequest(
                            toolType = config.type,
                            backendType = config.backendType,
                            serverAction = config.serverAction,
                            sourceImageUrl = null,
                            sourceImageUri = imageUri,
                            prompt = config.defaultPrompt,
                            templateId = null,
                            options = emptyMap(),
                            tokenCost = config.generation.tokenCost,
                            outputCount = config.generation.defaultOutputCount,
                            historyIdentity = config.historyIdentity
                        )

                        actions.createGenerationWithValidation(
                            request = request,
                            requiredFieldsValid = imageUri.isNotBlank()
                        )
                    }
                )
            }

            ToolType.PROMPT,
            ToolType.TEMPLATE,
            null -> {
                navController.popBackStack()
            }
        }
    }
}