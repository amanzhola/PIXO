package com.company.pixo.core.navigation

import android.net.Uri
import com.company.pixo.domain.model.ToolType
import com.company.pixo.feature.main.MainTab
import com.company.pixo.feature.photo.ToolPhotoSourceScreenVariant

sealed class AppRoute(
    val route: String
) {
    data object Splash : AppRoute("splash")
    data object Main : AppRoute("main?tab={tab}") {
        fun createRoute(tab: MainTab = MainTab.Tools): String {
            return "main?tab=${Uri.encode(tab.name)}"
        }
    }
    data object Paywall : AppRoute("paywall")
    data object TokenPaywall : AppRoute("token_paywall")
    data object Settings : AppRoute("settings")

    data object AiEnhancherOnboarding : AppRoute("ai_enhancher_onboarding")
    data object GlamMakeupOnboarding : AppRoute("glam_makeup_onboarding")
    data object RemoveObjectsOnboarding : AppRoute("remove_objects_onboarding")
    data object RemoveBackgroundOnboarding : AppRoute("remove_background_onboarding")
    data object SkinImproveOnboarding : AppRoute("skin_improve_onboarding")
    data object UpscaleImageOnboarding : AppRoute("upscale_image_onboarding")
    data object ChangeSceneOnboarding : AppRoute("change_scene_onboarding")
    data object HairStudioOnboarding : AppRoute("hair_studio_onboarding")
    data object SmileEditOnboarding : AppRoute("smile_edit_onboarding")
    data object GhostFaceOnboarding : AppRoute("ghost_face_onboarding")
    data object GhibliLookOnboarding : AppRoute("ghibli_look_onboarding")

    data object TemplatesOnboarding : AppRoute("templates_onboarding")
    data object PromptsOnboarding : AppRoute("prompts_onboarding")
    data object ProRate : AppRoute("pro_rate")
    data object RateSetup : AppRoute("rate_setup")

    data object ToolMain : AppRoute("tool_main/{toolType}?variant={variant}") {
        fun createRoute(
            toolType: ToolType,
            variant: ToolPhotoSourceScreenVariant = ToolPhotoSourceScreenVariant.Default
        ): String {
            return "tool_main/${Uri.encode(toolType.name)}?variant=${Uri.encode(variant.name)}"
        }
    }

    data object TemplateDetails : AppRoute("template_details/{templateId}") {
        fun createRoute(templateId: String): String {
            return "template_details/${Uri.encode(templateId)}"
        }
    }

    data object Editor : AppRoute("editor/{toolType}?imageUri={imageUri}") {
        fun createRoute(
            toolType: ToolType,
            imageUri: String
        ): String {
            return "editor/${Uri.encode(toolType.name)}?imageUri=${Uri.encode(imageUri)}"
        }
    }

    data object Generation : AppRoute("generation/{taskId}?toolType={toolType}&templateId={templateId}") {
        fun createRoute(
            taskId: String,
            toolType: ToolType,
            templateId: String? = null
        ): String {
            return "generation/${Uri.encode(taskId)}" +
                    "?toolType=${Uri.encode(toolType.name)}" +
                    "&templateId=${Uri.encode(templateId.orEmpty())}"
        }
    }

    data object Result : AppRoute("result/{generationId}?toolType={toolType}&templateId={templateId}") {
        fun createRoute(
            generationId: String,
            toolType: ToolType,
            templateId: String? = null
        ): String {
            return "result/${Uri.encode(generationId)}" +
                    "?toolType=${Uri.encode(toolType.name)}" +
                    "&templateId=${Uri.encode(templateId.orEmpty())}"
        }
    }

    data object Onboarding : AppRoute("onboarding")

    data object GlamMakeupEdit : AppRoute("glam_makeup_edit?imageUri={imageUri}") {
        fun createRoute(imageUri: String): String {
            return "glam_makeup_edit?imageUri=${Uri.encode(imageUri)}"
        }
    }

    data object RemoveObjectsEdit :
        AppRoute("remove_objects_edit?imageUri={imageUri}") {

        fun createRoute(imageUri: String): String {
            return "remove_objects_edit?imageUri=${Uri.encode(imageUri)}"
        }
    }

    data object HairStyleEdit :
        AppRoute("hair_style_edit?imageUri={imageUri}") {

        fun createRoute(imageUri: String): String {
            return "hair_style_edit?imageUri=${Uri.encode(imageUri)}"
        }
    }

    data object ChangeSceneEdit :
        AppRoute("change_scene_edit?imageUri={imageUri}") {

        fun createRoute(imageUri: String): String {
            return "change_scene_edit?imageUri=${Uri.encode(imageUri)}"
        }
    }

    data object AiEnhancerEdit :
        AppRoute("ai_enhancer_edit?imageUri={imageUri}") {

        fun createRoute(imageUri: String): String {
            return "ai_enhancer_edit?imageUri=${Uri.encode(imageUri)}"
        }
    }

    data object SkinImproveEdit :
        AppRoute("skin_improve_edit?imageUri={imageUri}") {

        fun createRoute(imageUri: String): String {
            return "skin_improve_edit?imageUri=${Uri.encode(imageUri)}"
        }
    }

    data object UpscaleImageEdit :
        AppRoute("upscale_image_edit?imageUri={imageUri}") {

        fun createRoute(imageUri: String): String {
            return "upscale_image_edit?imageUri=${Uri.encode(imageUri)}"
        }
    }

    data object GhibliEdit :
        AppRoute("ghibli_edit?imageUri={imageUri}") {

        fun createRoute(imageUri: String): String {
            return "ghibli_edit?imageUri=${Uri.encode(imageUri)}"
        }
    }

    data object GhostfaceEdit :
        AppRoute("ghostface_edit?imageUri={imageUri}") {

        fun createRoute(imageUri: String): String {
            return "ghostface_edit?imageUri=${Uri.encode(imageUri)}"
        }
    }

    data object SmileEditEdit :
        AppRoute("smile_edit_edit?imageUri={imageUri}") {

        fun createRoute(imageUri: String): String {
            return "smile_edit_edit?imageUri=${Uri.encode(imageUri)}"
        }
    }

    data object RemoveBackgroundEdit :
        AppRoute("remove_background_edit?imageUri={imageUri}") {

        fun createRoute(imageUri: String): String {
            return "remove_background_edit?imageUri=${Uri.encode(imageUri)}"
        }
    }

    data object TemplateGenerate :
        AppRoute("template_generate/{templateId}?imageUri={imageUri}") {

        fun createRoute(
            templateId: String,
            imageUri: String
        ): String {
            return "template_generate/${Uri.encode(templateId)}?imageUri=${Uri.encode(imageUri)}"
        }
    }
}