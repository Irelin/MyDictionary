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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home.R

@Composable
fun Profile() {
    Row(
        modifier = Modifier.Companion
            .clip(shape = RoundedCornerShape(12.dp))
            .background(Color(0xFFF7F6F5))
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Companion.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.account_image_default),
            contentDescription = null,
            contentScale = ContentScale.Companion.Crop,
            modifier = Modifier.Companion
                .size(100.dp)
                .clip(androidx.compose.foundation.shape.RoundedCornerShape(16.dp))
        )
        Column(
            modifier = Modifier.Companion
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = stringResource(R.string.profile_hi_user, "User"),
                fontSize = 20.sp,
                fontWeight = FontWeight.Companion.Bold
            )
            Text(
                text = stringResource(R.string.profile_learned_words, 50, 100),
                fontSize = 14.sp,
                color = Color.Companion.Gray
            )
            LinearProgressIndicator(
                progress = { 0.5f },
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                color = Color.Companion.Red,
                trackColor = Color.Companion.Gray
            )
        }
    }
}