package com.company.pixo.feature.onboarding

import com.company.pixo.R
import com.company.pixo.core.navigation.AppRoute
import com.company.pixo.domain.model.BeforeAfterAsset
import com.company.pixo.domain.model.RemoteImageAsset
import com.company.pixo.domain.model.ToolType

object PixoPremiumOnboardingDestinations {

    private const val ONBOARDING_ASSETS_BASE_URL =
        "https://raw.githubusercontent.com/amanzhola/PIXO/feature/onboarding-assets-backend/server-assets/assets/onboarding"

    val tools: List<PremiumOnboardingDestination> = listOf(
        PremiumOnboardingDestination(
            route = AppRoute.AiEnhancherOnboarding.route,
            toolType = ToolType.AI_ENHANCER,
            asset = BeforeAfterAsset.Combined(
                image = RemoteImageAsset.Remote(
                    url = "$ONBOARDING_ASSETS_BASE_URL/onb1.webp"
                )
            ),
            titleRes = R.string.onboarding_ai_photo_enhancer_title,
            subtitleRes = R.string.onboarding_ai_photo_enhancer_subtitle
        ),

        PremiumOnboardingDestination(
            route = AppRoute.GlamMakeupOnboarding.route,
            toolType = ToolType.GLAM_MAKEUP,
            asset = BeforeAfterAsset.Separate(
                before = RemoteImageAsset.Remote(
                    url = "$ONBOARDING_ASSETS_BASE_URL/tools_glam_makeup1.webp"
                ),
                after = RemoteImageAsset.Remote(
                    url = "$ONBOARDING_ASSETS_BASE_URL/tools_glam_makeup2.webp"
                )
            ),
            titleRes = R.string.onboarding_glam_makeover_title,
            subtitleRes = R.string.onboarding_glam_makeover_subtitle
        ),

        PremiumOnboardingDestination(
            route = AppRoute.RemoveObjectsOnboarding.route,
            toolType = ToolType.REMOVE_OBJECTS,
            asset = BeforeAfterAsset.Separate(
                before = RemoteImageAsset.Remote(
                    url = "$ONBOARDING_ASSETS_BASE_URL/tools_remove_objects1.webp"
                ),
                after = RemoteImageAsset.Remote(
                    url = "$ONBOARDING_ASSETS_BASE_URL/tools_remove_objects2.webp"
                )
            ),
            titleRes = R.string.onboarding_remove_objects_title,
            subtitleRes = R.string.onboarding_remove_objects_subtitle
        ),

        PremiumOnboardingDestination(
            route = AppRoute.RemoveBackgroundOnboarding.route,
            toolType = ToolType.REMOVE_BACKGROUND,
            asset = BeforeAfterAsset.Combined(
                image = RemoteImageAsset.Remote(
                    url = "$ONBOARDING_ASSETS_BASE_URL/onb9.webp"
                )
            ),
            titleRes = R.string.tool_remove_background,
            subtitleRes = R.string.onboarding_remove_background_subtitle
        ),

        PremiumOnboardingDestination(
            route = AppRoute.SkinImproveOnboarding.route,
            toolType = ToolType.SKIN_IMPROVE,
            asset = BeforeAfterAsset.Separate(
                before = RemoteImageAsset.Remote(
                    url = "$ONBOARDING_ASSETS_BASE_URL/tools_face1.webp"
                ),
                after = RemoteImageAsset.Remote(
                    url = "$ONBOARDING_ASSETS_BASE_URL/tools_face2.webp"
                )
            ),
            titleRes = R.string.tool_skin_improve,
            subtitleRes = R.string.onboarding_skin_improve_subtitle
        ),

        PremiumOnboardingDestination(
            route = AppRoute.UpscaleImageOnboarding.route,
            toolType = ToolType.UPSCALE_IMAGE,
            asset = BeforeAfterAsset.Combined(
                image = RemoteImageAsset.Remote(
                    url = "$ONBOARDING_ASSETS_BASE_URL/onb8.webp"
                )
            ),
            titleRes = R.string.tool_upscale_image,
            subtitleRes = R.string.onboarding_upscale_image_subtitle
        ),

        PremiumOnboardingDestination(
            route = AppRoute.ChangeSceneOnboarding.route,
            toolType = ToolType.CHANGE_SCENE,
            asset = BeforeAfterAsset.Separate(
                before = RemoteImageAsset.Remote(
                    url = "$ONBOARDING_ASSETS_BASE_URL/onb11_1.webp"
                ),
                after = RemoteImageAsset.Remote(
                    url = "$ONBOARDING_ASSETS_BASE_URL/onb11_2.webp"
                )
            ),
            titleRes = R.string.tool_change_scene,
            subtitleRes = R.string.onboarding_change_scene_subtitle
        ),

        PremiumOnboardingDestination(
            route = AppRoute.HairStudioOnboarding.route,
            toolType = ToolType.HAIR_STUDIO,
            asset = BeforeAfterAsset.Separate(
                before = RemoteImageAsset.Remote(
                    url = "$ONBOARDING_ASSETS_BASE_URL/tools_hair_studio1.webp"
                ),
                after = RemoteImageAsset.Remote(
                    url = "$ONBOARDING_ASSETS_BASE_URL/tools_hair_studio2.webp"
                )
            ),
            titleRes = R.string.tool_hair_studio,
            subtitleRes = R.string.onboarding_hair_studio_subtitle
        ),

        PremiumOnboardingDestination(
            route = AppRoute.SmileEditOnboarding.route,
            toolType = ToolType.SMILE_EDIT,
            asset = BeforeAfterAsset.Combined(
                image = RemoteImageAsset.Remote(
                    url = "$ONBOARDING_ASSETS_BASE_URL/tools_smile_edit.webp"
                )
            ),
            titleRes = R.string.tool_smile_edit,
            subtitleRes = R.string.onboarding_smile_edit_subtitle
        ),

        PremiumOnboardingDestination(
            route = AppRoute.GhostFaceOnboarding.route,
            toolType = ToolType.GHOSTFACE,
            asset = BeforeAfterAsset.Separate(
                before = RemoteImageAsset.Remote(
                    url = "$ONBOARDING_ASSETS_BASE_URL/tools_ghost_style1.webp"
                ),
                after = RemoteImageAsset.Remote(
                    url = "$ONBOARDING_ASSETS_BASE_URL/tools_ghost_style2.webp"
                )
            ),
            titleRes = R.string.tool_ghost_style,
            subtitleRes = R.string.onboarding_ghost_style_subtitle
        ),

        PremiumOnboardingDestination(
            route = AppRoute.GhibliLookOnboarding.route,
            toolType = ToolType.GHIBLI,
            asset = BeforeAfterAsset.Separate(
                before = RemoteImageAsset.Remote(
                    url = "$ONBOARDING_ASSETS_BASE_URL/tools_ghibli_look1.webp"
                ),
                after = RemoteImageAsset.Remote(
                    url = "$ONBOARDING_ASSETS_BASE_URL/tools_ghibli_look2.webp"
                )
            ),
            titleRes = R.string.tool_ghibli_look,
            subtitleRes = R.string.onboarding_ghibli_look_subtitle
        )
    )
}


//package com.company.pixo.feature.onboarding
//
//import com.company.pixo.R
//import com.company.pixo.core.navigation.AppRoute
//import com.company.pixo.domain.model.BeforeAfterAsset
//import com.company.pixo.domain.model.RemoteImageAsset
//import com.company.pixo.domain.model.ToolType
//
//object PixoPremiumOnboardingDestinations {
//
//    val tools: List<PremiumOnboardingDestination> = listOf(
//        PremiumOnboardingDestination(
//            route = AppRoute.AiEnhancherOnboarding.route,
//            toolType = ToolType.AI_ENHANCER,
////            asset = BeforeAfterAsset.Combined(
////                image = RemoteImageAsset.Local(R.drawable.onb1)
////            ),
//            asset = BeforeAfterAsset.Combined(
//                image = RemoteImageAsset.Remote(
//                    url = "http://192.168.0.177:8080/assets/onboarding/onb1.webp"
//                )
//            ),
//            titleRes = R.string.onboarding_ai_photo_enhancer_title,
//            subtitleRes = R.string.onboarding_ai_photo_enhancer_subtitle
//        ),
////        PremiumOnboardingDestination(
////            route = AppRoute.GlamMakeupOnboarding.route,
////            toolType = ToolType.GLAM_MAKEUP,
////            asset = BeforeAfterAsset.Separate(
////                before = RemoteImageAsset.Local(R.drawable.tools_glam_makeup1),
////                after = RemoteImageAsset.Local(R.drawable.tools_glam_makeup2)
////            ),
////            titleRes = R.string.onboarding_glam_makeover_title,
////            subtitleRes = R.string.onboarding_glam_makeover_subtitle
////        ),
//        PremiumOnboardingDestination(
//            route = AppRoute.GlamMakeupOnboarding.route,
//            toolType = ToolType.GLAM_MAKEUP,
//            asset = BeforeAfterAsset.Separate(
//                before = RemoteImageAsset.Remote(
//                    url = "http://192.168.0.177:8080/assets/onboarding/tools_glam_makeup1.webp"
//                ),
//                after = RemoteImageAsset.Remote(
//                    url = "http://192.168.0.177:8080/assets/onboarding/tools_glam_makeup2.webp"
//                )
//            ),
//            titleRes = R.string.onboarding_glam_makeover_title,
//            subtitleRes = R.string.onboarding_glam_makeover_subtitle
//        ),
//        PremiumOnboardingDestination(
//            route = AppRoute.RemoveObjectsOnboarding.route,
//            toolType = ToolType.REMOVE_OBJECTS,
//            asset = BeforeAfterAsset.Separate(
//                before = RemoteImageAsset.Local(R.drawable.tools_remove_objects1),
//                after = RemoteImageAsset.Local(R.drawable.tools_remove_objects2)
//            ),
//            titleRes = R.string.onboarding_remove_objects_title,
//            subtitleRes = R.string.onboarding_remove_objects_subtitle
//        ),
//        PremiumOnboardingDestination(
//            route = AppRoute.RemoveBackgroundOnboarding.route,
//            toolType = ToolType.REMOVE_BACKGROUND,
//            asset = BeforeAfterAsset.Combined(
//                image = RemoteImageAsset.Local(R.drawable.onb9)
//            ),
//            titleRes = R.string.tool_remove_background,
//            subtitleRes = R.string.onboarding_remove_background_subtitle
//        ),
//        PremiumOnboardingDestination(
//            route = AppRoute.SkinImproveOnboarding.route,
//            toolType = ToolType.SKIN_IMPROVE,
//            asset = BeforeAfterAsset.Separate(
//                before = RemoteImageAsset.Local(R.drawable.tools_face1),
//                after = RemoteImageAsset.Local(R.drawable.tools_face2)
//            ),
//            titleRes = R.string.tool_skin_improve,
//            subtitleRes = R.string.onboarding_skin_improve_subtitle
//        ),
//        PremiumOnboardingDestination(
//            route = AppRoute.UpscaleImageOnboarding.route,
//            toolType = ToolType.UPSCALE_IMAGE,
//            asset = BeforeAfterAsset.Combined(
//                image = RemoteImageAsset.Local(R.drawable.onb8)
//            ),
//            titleRes = R.string.tool_upscale_image,
//            subtitleRes = R.string.onboarding_upscale_image_subtitle
//        ),
//        PremiumOnboardingDestination(
//            route = AppRoute.ChangeSceneOnboarding.route,
//            toolType = ToolType.CHANGE_SCENE,
//            asset = BeforeAfterAsset.Separate(
//                before = RemoteImageAsset.Local(R.drawable.onb11_1),
//                after = RemoteImageAsset.Local(R.drawable.onb11_2)
//            ),
//            titleRes = R.string.tool_change_scene,
//            subtitleRes = R.string.onboarding_change_scene_subtitle
//        ),
//        PremiumOnboardingDestination(
//            route = AppRoute.HairStudioOnboarding.route,
//            toolType = ToolType.HAIR_STUDIO,
//            asset = BeforeAfterAsset.Separate(
//                before = RemoteImageAsset.Local(R.drawable.tools_hair_studio1),
//                after = RemoteImageAsset.Local(R.drawable.tools_hair_studio2)
//            ),
//            titleRes = R.string.tool_hair_studio,
//            subtitleRes = R.string.onboarding_hair_studio_subtitle
//        ),
//        PremiumOnboardingDestination(
//            route = AppRoute.SmileEditOnboarding.route,
//            toolType = ToolType.SMILE_EDIT,
//            asset = BeforeAfterAsset.Combined(
//                image = RemoteImageAsset.Local(R.drawable.tools_smile_edit)
//            ),
//            titleRes = R.string.tool_smile_edit,
//            subtitleRes = R.string.onboarding_smile_edit_subtitle
//        ),
//        PremiumOnboardingDestination(
//            route = AppRoute.GhostFaceOnboarding.route,
//            toolType = ToolType.GHOSTFACE,
//            asset = BeforeAfterAsset.Separate(
//                before = RemoteImageAsset.Local(R.drawable.tools_ghost_style1),
//                after = RemoteImageAsset.Local(R.drawable.tools_ghost_style2)
//            ),
//            titleRes = R.string.tool_ghost_style,
//            subtitleRes = R.string.onboarding_ghost_style_subtitle
//        ),
//        PremiumOnboardingDestination(
//            route = AppRoute.GhibliLookOnboarding.route,
//            toolType = ToolType.GHIBLI,
//            asset = BeforeAfterAsset.Separate(
//                before = RemoteImageAsset.Local(R.drawable.tools_ghibli_look1),
//                after = RemoteImageAsset.Local(R.drawable.tools_ghibli_look2)
//            ),
//            titleRes = R.string.tool_ghibli_look,
//            subtitleRes = R.string.onboarding_ghibli_look_subtitle
//        )
//    )
//}

//package com.company.pixo.feature.onboarding
//
//import com.company.pixo.R
//import com.company.pixo.core.navigation.AppRoute
//import com.company.pixo.domain.model.ToolType
//
//object PixoPremiumOnboardingDestinations {
//
//    val tools: List<PremiumOnboardingDestination> = listOf(
//        PremiumOnboardingDestination(
//            route = AppRoute.AiEnhancherOnboarding.route,
//            toolType = ToolType.AI_ENHANCER,
//            beforeImageRes = R.drawable.onb1,
//            titleRes = R.string.onboarding_ai_photo_enhancer_title,
//            subtitleRes = R.string.onboarding_ai_photo_enhancer_subtitle
//        ),
//        PremiumOnboardingDestination(
//            route = AppRoute.GlamMakeupOnboarding.route,
//            toolType = ToolType.GLAM_MAKEUP,
//            beforeImageRes = R.drawable.tools_glam_makeup1,
//            afterImageRes = R.drawable.tools_glam_makeup2,
//            titleRes = R.string.onboarding_glam_makeover_title,
//            subtitleRes = R.string.onboarding_glam_makeover_subtitle
//        ),
//        PremiumOnboardingDestination(
//            route = AppRoute.RemoveObjectsOnboarding.route,
//            toolType = ToolType.REMOVE_OBJECTS,
//            beforeImageRes = R.drawable.tools_remove_objects1,
//            afterImageRes = R.drawable.tools_remove_objects2,
//            titleRes = R.string.onboarding_remove_objects_title,
//            subtitleRes = R.string.onboarding_remove_objects_subtitle
//        ),
//        PremiumOnboardingDestination(
//            route = AppRoute.RemoveBackgroundOnboarding.route,
//            toolType = ToolType.REMOVE_BACKGROUND,
//            beforeImageRes = R.drawable.onb9,
//            titleRes = R.string.tool_remove_background,
//            subtitleRes = R.string.onboarding_remove_background_subtitle
//        ),
//        PremiumOnboardingDestination(
//            route = AppRoute.SkinImproveOnboarding.route,
//            toolType = ToolType.SKIN_IMPROVE,
//            beforeImageRes = R.drawable.tools_face1,
//            afterImageRes = R.drawable.tools_face2,
//            titleRes = R.string.tool_skin_improve,
//            subtitleRes = R.string.onboarding_skin_improve_subtitle
//        ),
//        PremiumOnboardingDestination(
//            route = AppRoute.UpscaleImageOnboarding.route,
//            toolType = ToolType.UPSCALE_IMAGE,
//            beforeImageRes = R.drawable.onb8,
//            titleRes = R.string.tool_upscale_image,
//            subtitleRes = R.string.onboarding_upscale_image_subtitle
//        ),
//        PremiumOnboardingDestination(
//            route = AppRoute.ChangeSceneOnboarding.route,
//            toolType = ToolType.CHANGE_SCENE,
//            beforeImageRes = R.drawable.onb11_1,
//            afterImageRes = R.drawable.onb11_2,
//            titleRes = R.string.tool_change_scene,
//            subtitleRes = R.string.onboarding_change_scene_subtitle
//        ),
//        PremiumOnboardingDestination(
//            route = AppRoute.HairStudioOnboarding.route,
//            toolType = ToolType.HAIR_STUDIO,
//            beforeImageRes = R.drawable.tools_hair_studio1,
//            afterImageRes = R.drawable.tools_hair_studio2,
//            titleRes = R.string.tool_hair_studio,
//            subtitleRes = R.string.onboarding_hair_studio_subtitle
//        ),
//        PremiumOnboardingDestination(
//            route = AppRoute.SmileEditOnboarding.route,
//            toolType = ToolType.SMILE_EDIT,
//            beforeImageRes = R.drawable.tools_smile_edit,
//            titleRes = R.string.tool_smile_edit,
//            subtitleRes = R.string.onboarding_smile_edit_subtitle
//        ),
//        PremiumOnboardingDestination(
//            route = AppRoute.GhostFaceOnboarding.route,
//            toolType = ToolType.GHOSTFACE,
//            beforeImageRes = R.drawable.tools_ghost_style1,
//            afterImageRes = R.drawable.tools_ghost_style2,
//            titleRes = R.string.tool_ghost_style,
//            subtitleRes = R.string.onboarding_ghost_style_subtitle
//        ),
//        PremiumOnboardingDestination(
//            route = AppRoute.GhibliLookOnboarding.route,
//            toolType = ToolType.GHIBLI,
//            beforeImageRes = R.drawable.tools_ghibli_look1,
//            afterImageRes = R.drawable.tools_ghibli_look2,
//            titleRes = R.string.tool_ghibli_look,
//            subtitleRes = R.string.onboarding_ghibli_look_subtitle
//        )
//    )
//}