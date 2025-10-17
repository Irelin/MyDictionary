package com.example.study.presentation.ui

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.study.ExerciseUiState
import com.example.study.di.StudyComponent
import com.example.study.presentation.StudyViewModel
import com.example.study.presentation.models.StudyScreenMode

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StudyScreen(mode: StudyScreenMode = StudyScreenMode.Memorize) {
    val viewmodelFactory = remember {
        val component = StudyComponent.create()
        component.provideViewModelFactory()
    }
    val viewModel = viewModel(StudyViewModel::class, factory = viewmodelFactory)

    val wordsListState by viewModel.wordsListUiState.collectAsState()

    val pagerState = rememberPagerState(pageCount = {
        wordsListState.words.size
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(
            modifier = Modifier
                .height(400.dp),
            state = pagerState,
        ) { page ->
            val word = wordsListState.words[page]
            Log.d("Dictionary", "Pager page = $page, word = ${word.word.originValue}")
            WordFlipCard(word) { viewModel.showTranslation(word.word.id) }
        }
        Text(
            modifier = Modifier.padding(vertical = 20.dp),
            text = "${pagerState.currentPage + 1}/${pagerState.pageCount}",
            fontSize = 20.sp
        )
        val resultButtonsVisible = mode == StudyScreenMode.Exercise &&
            wordsListState.words.getOrNull(pagerState.currentPage)?.translationVisible ?: false
        WordResultButtons(resultButtonsVisible)
    }
}

@Composable
fun WordResultButtons(resultButtonsVisible: Boolean) {
    Row(
        modifier = Modifier.height(102.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            visible = resultButtonsVisible,
            enter = scaleIn(animationSpec = tween(durationMillis = 500, delayMillis = 300)),
            exit = scaleOut()
        ) {
            WordStudyButton()
        }
        AnimatedVisibility(
            visible = resultButtonsVisible,
            enter = scaleIn(animationSpec = tween(durationMillis = 500, delayMillis = 300)),
            exit = scaleOut()
        ) {
            WordStudyButton(true)
        }
    }
}

@Composable
fun WordStudyButton(mirrorIcon: Boolean = false) {
    OutlinedButton(
        onClick = { },
        modifier = Modifier
            .padding(8.dp)
            .size(86.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(2.dp, Color(0xFFE8E7E6)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color(0xFF919090)
        )
    ) {
        Icon(
            Icons.Outlined.ThumbUp,
            contentDescription = null,
            modifier = Modifier
                .size(36.dp)
                .then(
                    if (mirrorIcon) Modifier.scale(scaleX = 1f, scaleY = -1f) else Modifier
                )
        )
    }
}


