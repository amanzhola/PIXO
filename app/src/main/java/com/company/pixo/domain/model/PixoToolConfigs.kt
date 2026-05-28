package com.company.pixo.domain.model

import com.company.pixo.R

object PixoToolConfigs {

    val imageTools: List<ToolConfig> = listOf(
        ToolConfig(
            type = ToolType.AI_ENHANCER,
            backendType = ToolBackendType.AI_ENHANCER,
            flowType = ToolFlowType.TOOL_WITH_PHOTO_AND_OPTIONS,
            optionsType = ToolOptionsType.NONE,
            titleRes = R.string.tool_ai_enhancer,
            subtitleRes = R.string.onboarding_ai_photo_enhancer_subtitle,
            previewBeforeRes = R.drawable.tools_ai_enhancher1_1,
            previewAfterRes = R.drawable.tools_ai_enhancher1_2,
            generation = ToolGenerationConfig(
                tokenCost = 2,
                defaultOutputCount = 1
            ),
            requiresUserPhoto = true,
            requiresPrompt = false,
            historyIdentity = "AI Enhancer",
            serverAction = "ai_enhancer"
        ),

        ToolConfig(
            type = ToolType.GLAM_MAKEUP,
            backendType = ToolBackendType.GLAM_MAKEUP,
            flowType = ToolFlowType.TOOL_WITH_PHOTO_AND_OPTIONS,
            optionsType = ToolOptionsType.MAKEUP_STYLE,
            titleRes = R.string.tool_glam_makeup,
            subtitleRes = R.string.onboarding_glam_makeover_subtitle,
            previewBeforeRes = R.drawable.tools_glam_makeup1,
            previewAfterRes = R.drawable.tools_glam_makeup2,
            generation = ToolGenerationConfig(
                tokenCost = 2,
                defaultOutputCount = 1
            ),
            optionConfig = ToolOptionConfig(
                type = ToolOptionsType.MAKEUP_STYLE,
                titleRes = R.string.glam_makeup_style_title,
                samples = listOf(
                    ToolOptionSample(
                        id = "natural_glow",
                        titleRes = R.string.glam_makeup_style_natural_glow,
                        serverValue = "Natural Glow",
                        example = "soft pink blush, nude lipstick"
                    ),
                    ToolOptionSample(
                        id = "gentle_glam",
                        titleRes = R.string.glam_makeup_style_gentle_glam,
                        serverValue = "Gentle Glam"
                    ),
                    ToolOptionSample(
                        id = "rich_glam",
                        titleRes = R.string.glam_makeup_style_rich_glam,
                        serverValue = "Rich Glam"
                    ),
                    ToolOptionSample(
                        id = "evening_look",
                        titleRes = R.string.glam_makeup_style_evening_look,
                        serverValue = "Evening Look"
                    )
                )
            ),
            requiresUserPhoto = true,
            requiresPrompt = true,
            historyIdentity = "Glam Makeup",
            serverAction = "glam_makeup"
        ),

        ToolConfig(
            type = ToolType.REMOVE_OBJECTS,
            backendType = ToolBackendType.REMOVE_OBJECTS,
            flowType = ToolFlowType.TOOL_WITH_PHOTO_AND_PROMPT,
            optionsType = ToolOptionsType.REMOVE_OBJECTS_TEXT,
            titleRes = R.string.tool_remove_objects,
            subtitleRes = R.string.onboarding_remove_objects_subtitle,
            previewBeforeRes = R.drawable.tools_remove_objects1,
            previewAfterRes = R.drawable.tools_remove_objects2,
            generation = ToolGenerationConfig(
                tokenCost = 2,
                defaultOutputCount = 1
            ),
            optionConfig = ToolOptionConfig(
                type = ToolOptionsType.REMOVE_OBJECTS_TEXT,
                titleRes = R.string.remove_objects_style_title,
                samples = listOf(
                    ToolOptionSample(
                        id = "remove_sample",
                        serverValue = "the person in red shirt, the watermark",
                        example = "Put away the umbrella"
                    )
                )
            ),
            requiresUserPhoto = true,
            requiresPrompt = true,
            historyIdentity = "Remove Objects",
            serverAction = "remove_objects",
            defaultPrompt = "Remove selected object from the image naturally."
        ),

        ToolConfig(
            type = ToolType.REMOVE_BACKGROUND,
            backendType = ToolBackendType.REMOVE_BACKGROUND,
            flowType = ToolFlowType.TOOL_WITH_PHOTO_AND_OPTIONS,
            optionsType = ToolOptionsType.BACKGROUND_MODE,
            titleRes = R.string.tool_remove_background,
            subtitleRes = R.string.onboarding_remove_background_subtitle,
            previewBeforeRes = R.drawable.tools_remove_background1,
            previewAfterRes = R.drawable.tools_remove_background2,
            generation = ToolGenerationConfig(
                tokenCost = 2,
                defaultOutputCount = 1
            ),
            optionConfig = ToolOptionConfig(
                type = ToolOptionsType.BACKGROUND_MODE,
                titleRes = R.string.remove_background_choose_type,
                samples = listOf(
                    ToolOptionSample(
                        id = "white",
                        titleRes = R.string.photo_mode_white,
                        serverValue = "White"
                    ),
                    ToolOptionSample(
                        id = "transparent",
                        titleRes = R.string.photo_mode_transparent,
                        serverValue = "Transparent"
                    )
                )
            ),
            requiresUserPhoto = true,
            requiresPrompt = false,
            historyIdentity = "Remove Background",
            serverAction = "remove_background"
        ),

        ToolConfig(
            type = ToolType.SKIN_IMPROVE,
            backendType = ToolBackendType.SKIN_IMPROVE,
            flowType = ToolFlowType.TOOL_WITH_PHOTO_AND_PROMPT,
            optionsType = ToolOptionsType.NONE,
            titleRes = R.string.tool_skin_improve,
            subtitleRes = R.string.onboarding_skin_improve_subtitle,
            previewBeforeRes = R.drawable.tools_skin_improve1,
            previewAfterRes = R.drawable.tools_skin_improve2,
            generation = ToolGenerationConfig(
                tokenCost = 2,
                defaultOutputCount = 1
            ),
            requiresUserPhoto = true,
            requiresPrompt = false,
            historyIdentity = "Skin Improve",
            serverAction = "skin_improve",
            defaultPrompt = "Smooth skin, reduce blemishes, and keep natural texture."
        ),

        ToolConfig(
            type = ToolType.UPSCALE_IMAGE,
            backendType = ToolBackendType.UPSCALE_IMAGE,
            flowType = ToolFlowType.TOOL_WITH_PHOTO_AND_PROMPT,
            optionsType = ToolOptionsType.NONE,
            titleRes = R.string.tool_upscale_image,
            subtitleRes = R.string.onboarding_upscale_image_subtitle,
            previewBeforeRes = R.drawable.tools_upscale_image1,
            previewAfterRes = R.drawable.tools_upscale_image2,
            generation = ToolGenerationConfig(
                tokenCost = 2,
                defaultOutputCount = 1
            ),
            requiresUserPhoto = true,
            requiresPrompt = false,
            historyIdentity = "Upscale Image",
            serverAction = "upscale_image",
            defaultPrompt = "Restore sharpness, clarity, and fine details while keeping the result natural."
        ),

        ToolConfig(
            type = ToolType.CHANGE_SCENE,
            backendType = ToolBackendType.CHANGE_SCENE,
            flowType = ToolFlowType.TOOL_WITH_PHOTO_AND_PROMPT,
            optionsType = ToolOptionsType.CHANGE_SCENE_TEXT,
            titleRes = R.string.tool_change_scene,
            subtitleRes = R.string.onboarding_change_scene_subtitle,
            previewBeforeRes = R.drawable.tools_change_scene1,
            previewAfterRes = R.drawable.tools_change_scene2,
            generation = ToolGenerationConfig(
                tokenCost = 2,
                defaultOutputCount = 1
            ),
            optionConfig = ToolOptionConfig(
                type = ToolOptionsType.CHANGE_SCENE_TEXT,
                titleRes = R.string.change_scene_describe_title,
                samples = listOf(
                    ToolOptionSample(
                        id = "scene_sample",
                        serverValue = "sunset beach, minimalist studio, forest",
                        example = "Forest"
                    )
                )
            ),
            requiresUserPhoto = true,
            requiresPrompt = true,
            historyIdentity = "Change Scene",
            serverAction = "change_scene"
        ),

        ToolConfig(
            type = ToolType.HAIR_STUDIO,
            backendType = ToolBackendType.HAIR_STUDIO,
            flowType = ToolFlowType.TOOL_WITH_PHOTO_AND_OPTIONS,
            optionsType = ToolOptionsType.HAIR_STYLE_DETAILS,
            titleRes = R.string.tool_hair_studio,
            subtitleRes = R.string.onboarding_hair_studio_subtitle,
            previewBeforeRes = R.drawable.tools_hair_studio,
            previewAfterRes = null,
            generation = ToolGenerationConfig(
                tokenCost = 2,
                defaultOutputCount = 1
            ),
            optionConfig = ToolOptionConfig(
                type = ToolOptionsType.HAIR_STYLE_DETAILS,
                titleRes = R.string.hair_style_details_title,
                samples = listOf(
                    ToolOptionSample(
                        id = "hairstyle",
                        serverValue = "bob, layers, pixie",
                        example = "Pixie"
                    ),
                    ToolOptionSample(
                        id = "length",
                        serverValue = "shoulder-length, long",
                        example = "Long"
                    ),
                    ToolOptionSample(
                        id = "color",
                        serverValue = "chestnut brown, platinum blonde",
                        example = "Platinum blonde"
                    )
                )
            ),
            requiresUserPhoto = true,
            requiresPrompt = true,
            historyIdentity = "Hair Studio",
            serverAction = "hair_studio"
        ),

        ToolConfig(
            type = ToolType.SMILE_EDIT,
            backendType = ToolBackendType.SMILE_EDIT,
            flowType = ToolFlowType.TOOL_WITH_PHOTO_AND_OPTIONS,
            optionsType = ToolOptionsType.SMILE_INTENSITY,
            titleRes = R.string.tool_smile_edit,
            subtitleRes = R.string.onboarding_smile_edit_subtitle,
            previewBeforeRes = R.drawable.tools_smile_edit,
            previewAfterRes = null,
            generation = ToolGenerationConfig(
                tokenCost = 2,
                defaultOutputCount = 1
            ),
            optionConfig = ToolOptionConfig(
                type = ToolOptionsType.SMILE_INTENSITY,
                titleRes = R.string.smile_edit_intensity_title,
                samples = listOf(
                    ToolOptionSample(id = "level_1", serverValue = "1"),
                    ToolOptionSample(id = "level_2", serverValue = "2"),
                    ToolOptionSample(id = "level_3", serverValue = "3"),
                    ToolOptionSample(id = "level_4", serverValue = "4"),
                    ToolOptionSample(id = "level_5", serverValue = "5")
                )
            ),
            requiresUserPhoto = true,
            requiresPrompt = false,
            historyIdentity = "Smile Edit",
            serverAction = "smile_edit",
            defaultPrompt = "We’ll improve your Smile with natural teeth whitening."
        ),

        ToolConfig(
            type = ToolType.GHIBLI,
            backendType = ToolBackendType.GHIBLI,
            flowType = ToolFlowType.TOOL_WITH_PHOTO_AND_PROMPT,
            optionsType = ToolOptionsType.NONE,
            titleRes = R.string.tool_ghibli_look,
            subtitleRes = R.string.onboarding_ghibli_look_subtitle,
            previewBeforeRes = R.drawable.tools_ghibli_look1,
            previewAfterRes = R.drawable.tools_ghibli_look2,
            generation = ToolGenerationConfig(
                tokenCost = 2,
                defaultOutputCount = 1
            ),
            requiresUserPhoto = true,
            requiresPrompt = false,
            historyIdentity = "Ghibli Look",
            serverAction = "ghibli",
            defaultPrompt = "Transform the image into a Studio Ghibli-inspired look: Soft, dreamy hand-drawn textures with warm, pastel colors and delicate outlines."
        ),

        ToolConfig(
            type = ToolType.GHOSTFACE,
            backendType = ToolBackendType.GHOSTFACE,
            flowType = ToolFlowType.TOOL_WITH_PHOTO_AND_PROMPT,
            optionsType = ToolOptionsType.NONE,
            titleRes = R.string.tool_ghost_style,
            subtitleRes = R.string.onboarding_ghost_style_subtitle,
            previewBeforeRes = R.drawable.tools_ghost_style1,
            previewAfterRes = R.drawable.tools_ghost_style2,
            generation = ToolGenerationConfig(
                tokenCost = 2,
                defaultOutputCount = 1
            ),
            requiresUserPhoto = true,
            requiresPrompt = false,
            historyIdentity = "Ghostface",
            serverAction = "ghostface",
            defaultPrompt = "Transform your photo into a spooky Ghost Face-inspired scene."
        )
    )

    val promptFlow: ToolConfig = ToolConfig(
        type = ToolType.PROMPT,
        backendType = ToolBackendType.PROMPT,
        flowType = ToolFlowType.PROMPT_FLOW,
        optionsType = ToolOptionsType.PROMPT_TEXT,
        titleRes = R.string.prompt_flow_title,
        subtitleRes = R.string.prompt_dialog_description,
        previewBeforeRes = R.drawable.onb15,
        previewAfterRes = null,
        generation = ToolGenerationConfig(
            tokenCost = 1,
            defaultOutputCount = 2,
            cartoonOutputCount = 4
        ),
        optionConfig = ToolOptionConfig(
            type = ToolOptionsType.PROMPT_TEXT,
            titleRes = R.string.prompt_dialog_title,
            samples = listOf(
                ToolOptionSample(
                    id = "cartoon",
                    titleRes = R.string.prompt_dialog_cartoon,
                    serverValue = "Make this into a cartoon style"
                ),
                ToolOptionSample(
                    id = "lighting",
                    titleRes = R.string.prompt_dialog_lighting,
                    serverValue = "Enhance the lighting and colors"
                ),
                ToolOptionSample(
                    id = "headshot",
                    titleRes = R.string.prompt_dialog_headshot,
                    serverValue = "Create a professional headshot"
                )
            )
        ),
        requiresUserPhoto = true,
        requiresPrompt = true,
        historyIdentity = "Prompt",
        serverAction = "prompt"
    )

    val templateTools: List<ToolConfig> = listOf(
        template("gloria_model", R.string.template_gloria_model, R.drawable.tools_gloria_model),
        template("cherry", R.string.template_cherry, R.drawable.template_cherry),
        template("travel_style", R.string.template_travel_style, R.drawable.template_travel_style),
        template("one_love", R.string.template_one_love, R.drawable.template_one_love),
        template("warm_day", R.string.template_warm_day, R.drawable.template_warm_day),
        template("pink_captivity", R.string.template_pink_captivity, R.drawable.template_pink_captivity),
        template("80s_gloss", R.string.template_80s_gloss, R.drawable.template_e80s_gloss),
        template("match_point", R.string.template_match_point, R.drawable.template_match_point),
        template("japan_breathe", R.string.template_japan_breathe, R.drawable.template_japan_breathe),
        template("easter_morning", R.string.template_easter_morning, R.drawable.template_easter_morning),
        template("sea_breathe", R.string.template_sea_breathe, R.drawable.template_sea_breathe),
        template("blossom", R.string.template_blossom, R.drawable.template_blossom),
        template("darning_noir", R.string.template_darning_noir, R.drawable.template_darning_noir),
        template("love_in_paris", R.string.template_love_in_paris, R.drawable.template_love_in_paris),
        template("queen_of_the_day", R.string.template_queen_of_the_day, R.drawable.template_queen_of_the_day),
        template("old_money_muse", R.string.template_old_money_muse, R.drawable.template_old_money_muse),
        template("sport_and_healthy", R.string.template_sport_and_healthy, R.drawable.template_sport_and_healthy),
        template("rapunzel_glow", R.string.template_rapunzel_glow, R.drawable.template_rapunzel_glow),
        template("safary", R.string.template_safari, R.drawable.template_safary),
        template("housewives", R.string.template_housewives, R.drawable.template_housewives),
        template("morning_routine", R.string.template_morning_routine, R.drawable.template_morning_routine),
        template("oscar", R.string.template_oscar, R.drawable.template_oscar),
        template("retro_style", R.string.template_retro_style, R.drawable.template_retro_style),
        template("metro_style", R.string.template_metro_style, R.drawable.template_metro_style)
    )

    val generationConfigs: List<ToolConfig> = imageTools + promptFlow + templateTools

    fun findByType(type: ToolType): ToolConfig? {
        return generationConfigs.firstOrNull { config ->
            config.type == type
        }
    }

    fun findTemplateById(templateId: String): ToolConfig? {
        return templateTools.firstOrNull { config ->
            config.templateId == templateId
        }
    }

    private fun template(
        templateId: String,
        titleRes: Int,
        previewRes: Int
    ): ToolConfig {
        return ToolConfig(
            type = ToolType.TEMPLATE,
            backendType = ToolBackendType.TEMPLATE,
            flowType = ToolFlowType.TEMPLATE_FLOW,
            optionsType = ToolOptionsType.TEMPLATE_ID,
            titleRes = titleRes,
            subtitleRes = R.string.templates_title,
            previewBeforeRes = previewRes,
            previewAfterRes = null,
            generation = ToolGenerationConfig(
                tokenCost = 2,
                defaultOutputCount = 1
            ),
            optionConfig = ToolOptionConfig(
                type = ToolOptionsType.TEMPLATE_ID,
                titleRes = R.string.templates_title,
                samples = listOf(
                    ToolOptionSample(
                        id = templateId,
                        serverValue = templateId
                    )
                )
            ),
            requiresUserPhoto = true,
            requiresPrompt = false,
            historyIdentity = templateId,
            serverAction = "template",
            templateId = templateId
        )
    }
}