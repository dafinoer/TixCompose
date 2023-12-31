package com.dafinrs.tixcompose.ui.pages.cinema

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CinemaScreenPage() {
    Scaffold {
        Box(modifier = Modifier.padding(it), contentAlignment = Alignment.Center) {
            Text(text = "Hello")
        }
    }
}