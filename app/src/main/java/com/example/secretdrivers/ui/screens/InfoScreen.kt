package com.example.secretdrivers.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.secretdrivers.R
import com.example.secretdrivers.ui.navigation.BottomNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Info",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                },
            )
        },
        bottomBar = { BottomNavBar(navController) }, // Add the BottomNavBar here
        content = { padding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .height(800.dp)
            ) {
                    HorizontalDivider(thickness = 1.dp, color = Color.Black)
                Image(
                    painter = painterResource(id = R.drawable.sd_logo_base),
                    contentDescription = "Secret Drivers Logo"
                )
                Text(
                    text = stringResource(id = R.string.app_info),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 26.dp, end = 26.dp, top = 12.dp, bottom = 0.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.rs_logo_light),
                    contentDescription = "Republic Services Icon",
                    modifier = Modifier
                        .padding(top = 12.dp, bottom = 32.dp)
                        .size(200.dp)
                )
            }
        }
    )
}
