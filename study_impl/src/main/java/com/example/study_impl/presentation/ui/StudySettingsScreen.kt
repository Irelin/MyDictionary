package com.example.study_impl.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.study_impl.R
import com.example.study_impl.presentation.models.StudyScreenMode

@Composable
fun StudySettingsScreen() {
    val studyMode: MutableState<StudyScreenMode?> = remember { mutableStateOf(null) }
    StudyMode {
        studyMode.value = it
    }
    if (studyMode.value != null) {
        StudyCategories()
        StartButton { }
    }
}

@Composable
private fun StudyMode(onSelectMode: (StudyScreenMode) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.study_mode_title)
        )
        Text(
            text = getStudyModeLabel(StudyScreenMode.Memorize),
            modifier = Modifier.clickable {
                onSelectMode(StudyScreenMode.Memorize)
            }
        )
        Text(
            text = getStudyModeLabel(StudyScreenMode.Exercise),
            modifier = Modifier.clickable {
                onSelectMode(StudyScreenMode.Exercise)
            }
        )
    }
}

@Composable
private fun getStudyModeLabel(mode: StudyScreenMode): String {
    return when (mode) {
        StudyScreenMode.Memorize -> stringResource(R.string.study_mode_memorize)
        StudyScreenMode.Exercise -> stringResource(R.string.study_mode_exercise)
    }
}

@Composable
fun StudyCategories() {

}

@Composable
fun StartButton(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        onClick = { onClick() },
        icon = { Icon(Icons.Filled.Check, "") },
        text = { Text(text = stringResource(R.string.study_btn_start)) },
    )
}