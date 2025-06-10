package com.example.study_api

import com.example.study_api.domain.usecase.GetStudyExercise

interface StudyApi {
    val getStudyExercise: GetStudyExercise
}