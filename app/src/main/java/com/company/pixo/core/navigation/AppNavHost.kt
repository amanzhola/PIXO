package com.company.pixo.core.navigation

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.PickVisualMediaRequest
import androidx.core.app.ActivityCompat
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.company.pixo.core.navigation.settings.openContactEmail
import com.company.pixo.core.navigation.settings.openPrivacyPolicy
import com.company.pixo.core.navigation.settings.openTermsOfUse
import com.company.pixo.core.navigation.settings.shareWithFriends
import com.company.pixo.core.navigation.template.templateDetailsDestination
import com.company.pixo.core.navigation.template.templateGenerateDestination
import com.company.pixo.core.navigation.tools.aiEnhancerEditDestination
import com.company.pixo.core.navigation.tools.changeSceneEditDestination
import com.company.pixo.core.navigation.tools.ghibliEditDestination
import com.company.pixo.core.navigation.tools.ghostfaceEditDestination
import com.company.pixo.core.navigation.tools.glamMakeupEditDestination
import com.company.pixo.core.navigation.tools.hairStyleEditDestination
import com.company.pixo.core.navigation.tools.removeBackgroundEditDestination
import com.company.pixo.core.navigation.tools.removeObjectsEditDestination
import com.company.pixo.core.navigation.tools.skinImproveEditDestination
import com.company.pixo.core.navigation.tools.smileEditEditDestination
import com.company.pixo.core.navigation.tools.upscaleImageEditDestination
import com.company.pixo.core.permissions.CameraImageUriFactory
import com.company.pixo.core.ui.PixoAiProcessingBottomSheetHost
import com.company.pixo.core.ui.permission.PixoCameraPermissionDeniedDialog
import com.company.pixo.core.ui.permission.PixoCameraPermissionSettingsDialog
import com.company.pixo.data.datastore.AppPreferencesDataSource
import com.company.pixo.domain.model.SubscriptionPlanType
import com.company.pixo.domain.repository.GenerationRepository
import com.company.pixo.domain.repository.HistoryRepository
import com.company.pixo.domain.repository.MediaRepository
import com.company.pixo.domain.repository.TokenRepository
import com.company.pixo.feature.prompt.PromptViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import kotlinx.coroutines.flow.first

@Composable
fun AppNavHost() {
    val promptViewModel: PromptViewModel = koinViewModel()
    val hasActiveSubscription = DEBUG_SUBSCRIPTION_PLAN != SubscriptionPlanType.None
    val isYearlySubscription = DEBUG_SUBSCRIPTION_PLAN == SubscriptionPlanType.Yearly

    val showTokenBalanceInMainTabs = isYearlySubscription
    val showTokenBalanceInSettings = hasActiveSubscription

    val preferences: AppPreferencesDataSource = koinInject()
    val tokenRepository: TokenRepository = koinInject()
    val generationRepository: GenerationRepository = koinInject()
    val mediaRepository: MediaRepository = koinInject()
    val historyRepository: HistoryRepository = koinInject()

    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    val context = LocalContext.current

    val activity = remember(context) {
        context.findActivity()
    }

    var pendingCameraTarget by remember {
        mutableStateOf<GalleryPickTarget?>(null)
    }

    var pendingCameraImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var repeatPaywallShown by rememberSaveable {
        mutableStateOf(false)
    }

    val tokenBalance by tokenRepository
        .getBalance()
        .collectAsState(initial = 0)

    Log.d("TokenDebug", "COMPOSE tokenBalance=$tokenBalance")

    LaunchedEffect(Unit) {
        Log.d("TokenDebug", "INIT started")

        if (DEBUG_INIT_TOKENS_IF_EMPTY) {
            val currentBalance = tokenRepository
                .getBalance()
                .first()

            Log.d("TokenDebug", "currentBalance before=$currentBalance")

            if (currentBalance == 0) {
                tokenRepository.setBalance(DEBUG_INITIAL_TOKENS)

                val afterBalance = tokenRepository
                    .getBalance()
                    .first()

                Log.d("TokenDebug", "after setBalance=$afterBalance")
            }
        }
    }

    var generationConsentState by remember {
        mutableStateOf(GenerationConsentState())
    }

    val actions = remember(
        navController,
        coroutineScope,
        preferences,
        tokenRepository,
        generationRepository,
        hasActiveSubscription
    ) {
        AppNavigationActions(
            navController = navController,
            coroutineScope = coroutineScope,
            preferences = preferences,
            tokenRepository = tokenRepository,
            generationRepository = generationRepository,
            hasActiveSubscription = hasActiveSubscription,
            onShowGenerationConsent = { request, onDismiss ->

                generationConsentState = GenerationConsentState(
                    show = true,
                    pendingRequest = request,
                    checked = false,
                    onDismiss = onDismiss,
                )
            }
        )
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        val target = pendingCameraTarget
        val imageUri = pendingCameraImageUri

        pendingCameraTarget = null
        pendingCameraImageUri = null

        if (success && target != null && imageUri != null) {
            actions.openPickedImage(
                target = target,
                imageUri = imageUri.toString()
            )
        }
    }

    var showCameraDeniedDialog by remember {
        mutableStateOf(false)
    }

    var showCameraSettingsDialog by remember {
        mutableStateOf(false)
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        val target = pendingCameraTarget

        if (granted && target != null) {
            val imageUri = CameraImageUriFactory.createImageUri(context)

            pendingCameraImageUri = imageUri
            cameraLauncher.launch(imageUri)
        } else {
            pendingCameraImageUri = null

            val shouldShowRationale = ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                android.Manifest.permission.CAMERA
            )

            if (shouldShowRationale) {
                showCameraDeniedDialog = true
            } else {
                showCameraSettingsDialog = true
            }
        }
    }

    fun openRealCamera(
        target: GalleryPickTarget
    ) {
        pendingCameraTarget = target

        val permissionGranted = ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        if (permissionGranted) {
            val imageUri = CameraImageUriFactory.createImageUri(context)

            pendingCameraImageUri = imageUri
            cameraLauncher.launch(imageUri)
        } else {
            cameraPermissionLauncher.launch(
                android.Manifest.permission.CAMERA
            )
        }
    }

    var pendingGalleryTarget by remember {
        mutableStateOf<GalleryPickTarget?>(null)
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        val target = pendingGalleryTarget

        pendingGalleryTarget = null

        Log.d("PromptGallery", "picker result uri=$uri target=$target")

        if (uri != null && target != null) {
            when (target) {
                GalleryPickTarget.Prompt -> {
                    promptViewModel.setImageUri(uri.toString())
                }

                else -> {
                    actions.openPickedImage(
                        target = target,
                        imageUri = uri.toString()
                    )
                }
            }
        }
    }

    fun openGalleryPicker(target: GalleryPickTarget) {
        pendingGalleryTarget = target

        galleryLauncher.launch(
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.ImageOnly
            )
        )
    }

    val useGenericTemplateTitleState = rememberSaveable {
        mutableStateOf(USE_GENERIC_TEMPLATE_TITLE)
    }

    NavHost(
        navController = navController,
        startDestination = AppRoute.Splash.route,
        modifier = Modifier.fillMaxSize()
    ) {
        splashDestination(
            navController = navController,
            preferences = preferences,
            hasActiveSubscription = hasActiveSubscription
        )

        onboardingDestination(
            navController = navController,
            preferences = preferences,
            coroutineScope = coroutineScope
        )
        tokenBalance.let {
            mainDestination(
                navController = navController,
                preferences = preferences,
                tokenRepository = tokenRepository,
                coroutineScope = coroutineScope,
                actions = actions,
                hasActiveSubscription = hasActiveSubscription,
                showTokenBalanceInMainTabs = showTokenBalanceInMainTabs,
                repeatPaywallShown = repeatPaywallShown,
                onRepeatPaywallShownChange = {
                    repeatPaywallShown = it
                },
                openGalleryPicker = { target: GalleryPickTarget ->
                    openGalleryPicker(target)
                },
                promptViewModel = promptViewModel,
                generationRepository = generationRepository,
                historyRepository = historyRepository,
            )
        }

        tokenBalance.let {
            settingsDestination(
                navController = navController,
                actions = actions,
                hasActiveSubscription = hasActiveSubscription,
                showTokenBalanceInSettings = showTokenBalanceInSettings,
                tokenRepository = tokenRepository,
                onContactUsClick = { activity.openContactEmail() },
                onShareWithFriendsClick = { activity.shareWithFriends() },
                onPrivacyPolicyClick = { activity.openPrivacyPolicy() },
                onTermsOfUseClick = { activity.openTermsOfUse() }
            )
        }

        paywallDestination(
            navController = navController
        )

        tokenPaywallDestination(
            navController = navController,
            tokenRepository = tokenRepository
        )

        premiumOnboardingDestinations(
            preferences = preferences,
            coroutineScope = coroutineScope,
            actions = actions
        )

        rateOnboardingDestination(
            navController = navController
        )

        toolMainDestination(
            navController = navController,
            openCameraFlow = { toolType ->
                openRealCamera(
                    GalleryPickTarget.Tool(toolType)
                )
            },
            openGalleryPicker = { toolType ->
                openGalleryPicker(
                    GalleryPickTarget.Tool(toolType)
                )
            }
        )

        editorDestination(
            navController = navController
        )
        generationDestination(
            navController = navController,
            generationRepository = generationRepository
        )

        resultDestination(
            navController = navController,
            generationRepository = generationRepository,
            mediaRepository = mediaRepository,
            historyRepository = historyRepository,
            actions = actions,
            useGenericTemplateTitleState = useGenericTemplateTitleState
        )

        glamMakeupEditDestination(
            navController = navController,
            actions = actions
        )

        removeObjectsEditDestination(
            navController = navController,
            actions = actions
        )

        hairStyleEditDestination(
            navController = navController,
            actions = actions
        )

        changeSceneEditDestination(
            navController = navController,
            generationRepository = generationRepository
        )

        aiEnhancerEditDestination(
            navController = navController,
            actions = actions
        )

        skinImproveEditDestination(
            navController = navController,
            actions = actions
        )

        upscaleImageEditDestination(
            navController = navController,
            actions = actions
        )

        ghibliEditDestination(
            navController = navController,
            actions = actions
        )

        ghostfaceEditDestination(
            navController = navController,
            actions = actions
        )

        smileEditEditDestination(
            navController = navController,
            actions = actions
        )

        removeBackgroundEditDestination(
            navController = navController,
            actions = actions
        )

        templateDetailsDestination(
            navController = navController,
            useGenericTemplateTitleState = useGenericTemplateTitleState,
            openCameraFlow = { templateId ->
                openRealCamera(GalleryPickTarget.Template(templateId))
            },
            openGalleryPicker = { target ->
                openGalleryPicker(target)
            },
            actions = actions
        )

        templateGenerateDestination(
            navController = navController,
            useGenericTemplateTitleState = useGenericTemplateTitleState,
            openCameraFlow = { templateId ->
                openRealCamera(GalleryPickTarget.Template(templateId))
            },
            openGalleryPicker = { target ->
                openGalleryPicker(target)
            },
            actions = actions
        )
    }

    if (showCameraDeniedDialog) {
        PixoCameraPermissionDeniedDialog(
            onCancelClick = {
                showCameraDeniedDialog = false
                pendingCameraTarget = null
                pendingCameraImageUri = null
            },
            onTryAgainClick = {
                showCameraDeniedDialog = false

                cameraPermissionLauncher.launch(
                    android.Manifest.permission.CAMERA
                )
            }
        )
    }

    if (showCameraSettingsDialog) {
        PixoCameraPermissionSettingsDialog(
            onCancelClick = {
                showCameraSettingsDialog = false
                pendingCameraTarget = null
                pendingCameraImageUri = null
            },
            onOpenSettingsClick = {
                showCameraSettingsDialog = false

                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts(
                        "package",
                        context.packageName,
                        null
                    )
                )

                context.startActivity(intent)
            }
        )
    }
    PixoAiProcessingBottomSheetHost(
        show = generationConsentState.show,
        checked = generationConsentState.checked,
        onCheckedChange = { checked ->
            generationConsentState = generationConsentState.copy(
                checked = checked
            )
        },

        onCancelClick = {
            val onDismiss = generationConsentState.onDismiss

            generationConsentState = GenerationConsentState()

            actions.markGenerationConsentCancelled()

            onDismiss?.invoke()
        },
        onContinueClick = {
            val request = generationConsentState.pendingRequest
                ?: return@PixoAiProcessingBottomSheetHost

            generationConsentState = GenerationConsentState()

            actions.createGenerationAfterConsent(request)
        }
    )
}
