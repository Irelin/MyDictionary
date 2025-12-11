package com.example.home.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home.R

@Composable
fun Profile(userName: String) {
    Row(
        modifier = Modifier.Companion
            .padding(top = 12.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(Color(0xFFF7F6F5))
            .fillMaxWidth()
            .padding(all = 8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Companion.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = null,
            modifier = Modifier.size(90.dp).background(Color.White)
                .clip(androidx.compose.foundation.shape.RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.Companion
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = stringResource(R.string.profile_hi_user, " $userName"),
                fontSize = 22.sp
            )
            Text(
                text = stringResource(R.string.profile_learned_words, 50, 100),
                fontSize = 14.sp,
                color = colorResource(R.color.text_secondary_color)
            )
            LinearProgressIndicator(
                progress = { 0.5f },
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                color = colorResource(R.color.color_selected),
                trackColor = colorResource(R.color.color_unselected)
            )
        }
    }
}