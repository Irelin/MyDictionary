package com.example.study.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.study.di.StudyComponent
import com.example.study.presentation.ui.StudyWordUI
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StudyScreen() {
    val viewmodelFactory = remember {
        val component = StudyComponent.create()
        component.provideViewModelFactory()
    }
    val viewModel = viewModel(StudyViewModel::class, factory = viewmodelFactory)

    val wordsListState by viewModel.wordsListUiState.collectAsState()

    val pagerState = rememberPagerState(pageCount = {
        wordsListState.words.size
    })
    FlipAnimation()

    /*Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(
            modifier = Modifier.weight(0.85f),
            state = pagerState
        ) { page ->
            val word = wordsListState.words[page]
            ElevatedCard(
                modifier = Modifier
                    .padding(start = 32.dp, end = 32.dp)
                    .clickable { viewModel.showTranslation(word.word.id) }
                    //.fillMaxWidth()
                    .aspectRatio(1f)
                    .graphicsLayer {
                        // Calculate the absolute offset for the current page from the
                        // scroll position. We use the absolute value which allows us to mirror
                        // any effects for both directions
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        // We animate the alpha, between 50% and 100%
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                WordCardContent(word, viewModel::setAnswerCorrect)
            }
        }
        Column(
            modifier = Modifier
                .weight(0.1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "${pagerState.currentPage + 1}/${pagerState.pageCount}", fontSize = 20.sp)
        }
        *//*HorizontalPager(state = pagerState, pageSpacing = 20.dp) { page ->
            val studyWord = wordsListState.words[page]
            Card(
                Modifier
                    .size(width = 280.dp, height = 360.dp)
                    .graphicsLayer {
                        // Calculate the absolute offset for the current page from the
                        // scroll position. We use the absolute value which allows us to mirror
                        // any effects for both directions
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        // We animate the alpha, between 50% and 100%
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                WordCardContent(studyWord, viewModel::setAnswerCorrect)
            }
        }*//*
    }*/
}

@Composable
fun WordCardContent(studyWord: StudyWordUI, onAnswerIconClick: (Long, Boolean) -> Unit) {
    //todo flipcard
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = studyWord.word.originValue,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
        )
        Text(
            text = if (studyWord.isTranslationVisible) studyWord.word.translatedValue else "",
            fontSize = 20.sp,
            color = Color.DarkGray
        )
        /*Row(modifier = Modifier.weight(0.3f), verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = { onAnswerIconClick(studyWord.word.id, true) },
                modifier = Modifier.weight(0.5f)
            ) {
                Icon(
                    Icons.Filled.Check,
                    contentDescription = null, modifier = Modifier.size(200.dp),
                    tint = if (studyWord.isAnswerCorrect) Color.Green else Color.Gray
                )
            }
            IconButton(
                onClick = { onAnswerIconClick(studyWord.word.id, false) },
                modifier = Modifier.weight(0.5f)
            ) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = null, modifier = Modifier.size(200.dp),
                    tint = if (studyWord.isAnswerWrong) Color.Red else Color.Gray
                )
            }
        }*/
    }
}

@OptIn(ExperimentalFoundationApi::class)
private val threePagesPerViewport = object : PageSize {
    override fun Density.calculateMainAxisPageSize(
        availableSpace: Int,
        pageSpacing: Int
    ): Int {
        return (availableSpace - 2 * pageSpacing) / 3
    }
}
