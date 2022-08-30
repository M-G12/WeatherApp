package com.gahribe.weatherapp.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.DateTimeFormatter

@Composable
fun WeatherCard(
    state: WeatherState,
    backgroundColor: Color,
) {
    state.weatherInfo?.currentWeatherData?.let { data ->
        Log.d("Gharibe", "Data is ${data.weatherType.weatherDesc}")
        Card(
            backgroundColor = backgroundColor,
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Today ${
                        data.time.format(DateTimeFormatter.ofPattern("HH:mm"))
                    }",
                    modifier = Modifier.align(Alignment.End),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = data.weatherType.iconRes),
                    contentDescription = "weather type",
                    modifier = Modifier.width(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${data.tempCelsius}°C",
                    fontSize = 50.sp,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = data.weatherType.weatherDesc,
                    fontSize = 20.sp,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDisplay(
                        value = data.pressure.toInt(),
                        unit = "hpa",
                        icon = ImageVector.vectorResource(id = com.gahribe.weatherapp.R.drawable.ic_pressure),
                        iconTint = Color.White
                    )
                    WeatherDataDisplay(
                        value = data.humidity.toInt(),
                        unit = "%",
                        icon = ImageVector.vectorResource(id = com.gahribe.weatherapp.R.drawable.ic_drop),
                        iconTint = Color.White
                    )
                    WeatherDataDisplay(
                        value = data.windSpeed.toInt(),
                        unit = "km/h",
                        icon = ImageVector.vectorResource(id = com.gahribe.weatherapp.R.drawable.ic_wind),
                        iconTint = Color.White
                    )
                }
            }
        }
    }
}