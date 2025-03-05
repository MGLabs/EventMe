package com.mglabs.eventme.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mglabs.eventme.navigation.EMNavHost

@Composable
fun EMApp(
) {
    EMAppInternal()
}

@Composable
internal fun EMAppInternal(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {}
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding)
        ) {

            EMNavHost()
        }
    }
}