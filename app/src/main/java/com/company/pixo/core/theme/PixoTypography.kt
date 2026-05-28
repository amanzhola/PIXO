package com.company.pixo.core.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val PixoTypography = Typography(

    // PIXO logo text
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.26).sp
    ),

    // screen title // Card title: Image Lab
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(510),
        fontSize = 20.sp,
        lineHeight = 25.sp,
        letterSpacing = (-0.45).sp
    ),

    // Buttons
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(590),
        fontSize = 19.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.43).sp
    ),

    // Token badge semibold
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(590),
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.23).sp
    ),

    // Token balance regular
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.23).sp
    ),

    // Bottom navigation
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(590),
        fontSize = 11.sp,
        lineHeight = 13.sp,
        letterSpacing = 0.06.sp
    ),

    // Try It
        bodyLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 21.sp,
            letterSpacing = (-0.31).sp
        ),

    // Card subtitle: Create unique photos
        bodySmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            lineHeight = 18.sp,
            letterSpacing = (-0.08).sp
        ),

    // small picture slider
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(590),
        fontSize = 17.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.43).sp
    ),

    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.26).sp
    ),

    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        letterSpacing = 0.38.sp
    ),

)