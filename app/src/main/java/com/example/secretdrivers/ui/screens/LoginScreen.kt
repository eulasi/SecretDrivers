package com.example.secretdrivers.ui.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.secretdrivers.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


@Preview(showSystemUi = false, showBackground = true)
@Composable
fun PreviewLoginScreen() {
}

@Composable
fun LoginScreen(
    navController: NavController, gso: GoogleSignInOptions, requestCode: Int
) {
    val context = LocalContext.current
    val googleSignInClient = remember { GoogleSignIn.getClient(context, gso) }

    fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        (context as Activity).startActivityForResult(signInIntent, requestCode)
    }

    Image(
        painter = painterResource(id = R.drawable.sd_background),
        contentDescription = "App Auth Screen Background",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .height(410.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.sd_logo_icon),
                contentDescription = "App Logo Icon",
                modifier = Modifier
                    .padding(top = 54.dp)
                    .offset(x = 0.dp, y = 0.dp)
                    .size(135.dp),
            )
            Text(
                color = Color.White,
                text = stringResource(id = R.string.app_name),
                fontSize = 40.sp,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
            Text(
                color = Color.White,
                text = stringResource(id = R.string.app_slogan),
                fontSize = 20.sp,
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier
            )
            Text(
                color = Color.White,
                text = stringResource(id = R.string.app_description),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 14.dp, top = 4.dp, bottom = 0.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = { signInWithGoogle() },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.width(350.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "Google Icon",
                        modifier = Modifier
                            .padding(10.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.google_auth),
                        fontSize = 18.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, end = 10.dp)
                    )
                }
            }
            Spacer(
                modifier = Modifier.padding(12.dp)
            )
            // TODO: Add Facebook FireBase Auth
//            Button(
//                onClick = { /*TODO*/ },
//                shape = RoundedCornerShape(12.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
//                modifier = Modifier
//                    .width(350.dp)
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceEvenly,
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.ic_facebook),
//                        contentDescription = "Facebook Icon",
//                        modifier = Modifier
//                            .padding(10.dp)
//                    )
//                    Text(
//                        text = stringResource(id = R.string.facebook_auth),
//                        fontSize = 18.sp,
//                        fontFamily = FontFamily.SansSerif,
//                        color = Color.Black,
//                        modifier = Modifier
//                            .padding(top = 10.dp, bottom = 10.dp, end = 10.dp)
//                    )
//                }
//            }
            Image(
                painter = painterResource(id = R.drawable.ic_republic_services),
                contentDescription = "Republic Services Icon",
                modifier = Modifier
                    .padding(top = 22.dp, bottom = 32.dp)
                    .size(100.dp)
            )
        }
    }
}