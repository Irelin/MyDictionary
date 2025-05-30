package com.example.study.presentation.ui

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.study.di.StudyComponent
import com.example.study.presentation.StudyViewModel

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
    }
}

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

