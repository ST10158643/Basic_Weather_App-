package za.co.varsitycollege.st10158643.basicweatherapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import kotlin.concurrent.thread
import za.co.varsitycollege.st10158643.basicweatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var fiveDayList = mutableListOf<Forecast>()
    val LOGGING_TAG = "weatherDATA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // add an event handler to open the Accu Weather website on click
//        binding.ivAccuweather.setOnClickListener {
//            val intent = Intent(
//                Intent.ACTION_VIEW,
//                Uri.parse("http://www.accuweather.com/")
//            )
//            startActivity(intent)
//        }

        //HOLD'S JSON DATA FROM THE API
//        thread {
//            val weather = try {
//                buildURLForWeather()?.readText()
//            } catch (e: Exception) {
//                return@thread
//            }


            // Extract data from the JSON object
           // val data = rootWeatherData.getString("Headline")
            //consumeJson(data)

            runOnUiThread {
//                consumeJson(weather?:"")
//            binding.ivAccuweather.setOnClickListener{
//                val intent = Intent(Intent.ACTION_VIEW,
//                    Uri.parse("http://www.accuweather.com/"))
//            }
            //startActivity(intent)
            }


        //}
    }
 //USING GSON
/*Use Gson to parse the JSON data into instances of your data classes.*/
fun consumeJson(weatherJSON: String?) {
    if (weatherJSON != null) {
        val gson = Gson()
        val weatherData = gson.fromJson<ExampleJson2KtKotlin>(weatherJSON, ExampleJson2KtKotlin::class.java)
        for(forecast in weatherData.DailyForecasts) {
         //   binding.tvWeather.append("Date: " +
             /*       forecast.Date?.substring(0, 10) +
                    " Min: " +
                    forecast.Temperature?.Minimum?.Value +
                    " Max: " +
                    forecast.Temperature?.Maximum?.Value +
                    "\n")*/
        }
    }
}
    //USING JSON
   /* fun consumeJson(weatherJSON: String) {

        if (fiveDayList.isNotEmpty()) {
            fiveDayList.clear()
        }
        try {
            // Get the root JSON object
            val rootWeatherData = JSONObject(weatherJSON)

            // Find the daily forecasts array
            val fiveDayForecast = rootWeatherData.getJSONArray("DailyForecasts")

            // Get data from each entry in the array
            for (i in 0 until fiveDayForecast.length()) {
                val dailyWeather = fiveDayForecast.getJSONObject(i)

                // Get date
                val date = dailyWeather.getString("Date")
                Log.i(LOGGING_TAG, "consumeJson: Date$date")

                // Get minimum temperature
                val temperatureObject = dailyWeather.getJSONObject("Temperature")
                val minTempObject = temperatureObject.getJSONObject("Minimum")
                val minTemp = minTempObject.getDouble("Value")
                Log.i(LOGGING_TAG, "consumeJson: minTemp$minTemp")

                // Get maximum temperature
                val maxTempObject = temperatureObject.getJSONObject("Maximum")
                val maxTemp = maxTempObject.getDouble("Value")
                Log.i(LOGGING_TAG, "consumeJson: maxTemp$maxTemp")

                // Create a Forecast object
                val forecastObject = Forecast(
                    date = date,
                    minimumTemperature = minTemp,
                    maximumTemperature = maxTemp
                )

                fiveDayList.add(forecastObject)
                binding.tvWeather.append(
                    "Date: $date Min: $minTemp Max: $maxTemp\n"
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }*/

    data class Forecast(
        val date: String = "",
        val minimumTemperature: Double = 0.0,
        val maximumTemperature: Double = 0.0,
        val dayIcon: Int = 0,
        val dayIconPhrase: String = "",
        val nightIcon: Int = 0,
        val nightIconPhrase: String = ""
    )
}