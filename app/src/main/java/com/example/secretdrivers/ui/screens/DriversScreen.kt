package com.example.secretdrivers.ui.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.secretdrivers.data.model.driver.Route
import com.example.secretdrivers.ui.navigation.BottomNavBar
import com.example.secretdrivers.ui.vm.DriverViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriversScreen(
    navController: NavController,
    viewModel: DriverViewModel,
    modifier: Modifier = Modifier,
    // drivers: List<String> = List(40) { "$it" }
) {
    //   val viewModel = ViewModelProvider(this).get(DriverViewModel::class.java)
    val drivers by viewModel.driverModels.collectAsState(emptyList())
    val routes by viewModel.routes.collectAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Drivers",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    TextButton(
                        onClick = { viewModel.sortDriversByLastName() },
                        modifier = Modifier.padding(end = 4.dp, top = 0.dp)
                    ) {
                        Text(
                            "Filter by Last Name",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 6.dp, end = 4.dp)
                        )
                    }
                }
            )
        },
        bottomBar = { BottomNavBar(navController) },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding), // Apply the padding provided by Scaffold
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                HorizontalDivider(thickness = 1.dp, color = Color.Black)

                LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
                    items(items = drivers) { driver ->
                        val route = routes?.firstOrNull()
                        val routeOptions = routes.find { it.id.toString() == driver.id }
                        DriverCard(
                            driverName = driver?.name ?: "",
                            driverID = driver?.id ?: "",
                            routeName = routeOptions?.name ?: "",
                            type = routeOptions?.type ?: "",
                            routeID = routeOptions?.id ?: 0,
                            routes = routes
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun DriverCard(
    driverName: String,
    driverID: String,
    routeName: String,
    type: String,
    routeID: Int,
    routes: List<Route>
) {
    var expanded by remember { mutableStateOf(false) }

    val extraPadding by animateDpAsState(
        if (expanded) 58.dp else 0.dp, label = ""
    )

    val routeText = when {
        driverID.toInt() == routeID -> "Route: $routeName"
        driverID.toInt() % 2 == 0 -> {
            val firstRTypeRoute = routes.find { it.type == "R" }?.name ?: "Unknown R Type Route"
            "First $firstRTypeRoute"
        }

        driverID.toInt() % 5 == 0 -> {
            val secondCTypeRoute = routes.find { it.type == "C" }?.name ?: "Unknown C Type Route"
            "Second $secondCTypeRoute"
        }

        else -> {
            val lastITypeRoute = routes.find { it.type == "I" }?.name ?: "Unknown I Type Route"
            "Last $lastITypeRoute"
        }
    }

    Surface(
        color = Color.Transparent,
        modifier = Modifier
            .padding(top = 22.dp, start = 24.dp, end = 24.dp, bottom = 0.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(16.dp))

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(bottom = extraPadding)

            ) {
                Text(
                    text = "$driverName",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 2.dp)
                )
                Text(
                    text = "Driver ID: $driverID ",
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(top = 2.dp, start = 24.dp, end = 24.dp, bottom = 20.dp)
                )
                if (expanded) {
                    Text(
                        text = routeText,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 4.dp, start = 24.dp, end = 24.dp, bottom = 0.dp)
                    )
                }
            }
            ElevatedButton(
                onClick = { expanded = !expanded },
                modifier = Modifier
                    .padding(end = 24.dp, top = 24.dp)
                    .border(1.dp, Color.Transparent, RoundedCornerShape(60.dp))
                    .background(color = Color.Transparent)
                    .height(50.dp)
                    .width(75.dp)
                    .align(Alignment.Top)
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowDown else Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    modifier = Modifier
                        .size(34.dp)
                )
            }
        }
    }
}