package com.example.study_impl.presentation.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.study_impl.presentation.models.StudyWordUI

@Composable
fun WordFlipCard(studyWord: StudyWordUI, onClick: () -> Unit) {
    val animDuration = 500
    val zAxisDistance = 50f //distance between camera and Card

    val frontColor = animateColorAsState(
        targetValue = if (studyWord.translationVisible) Color(0xFFB0E0B1) else Color(0xFF78CB94),
        animationSpec = tween(durationMillis = animDuration, easing = EaseInOut),
        label = ""
    )

    // rotate Y-axis with animation
    val rotateCardY = animateFloatAsState(
        targetValue = if (studyWord.translationVisible) 180f else 0f,
        animationSpec = tween(durationMillis = animDuration, easing = EaseInOut),
        label = ""
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(300.dp)
                .graphicsLayer {
                    rotationY = rotateCardY.value
                    cameraDistance = zAxisDistance
                }
                .clip(RoundedCornerShape(24.dp))
                .clickable { onClick() }
                .background(frontColor.value),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.graphicsLayer {
                    rotationY = if (rotateCardY.value < 90f) 0f else 180f
                },
                text = if (rotateCardY.value < 90f) studyWord.word.originValue else studyWord.word.translatedValue,
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}