package za.co.varsitycollege.st10158643.basicweatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlin.concurrent.thread

/**
 * A fragment representing a list of Items.
 */
class DailyForecastsFragment : Fragment() {

    // Number of columns in the grid layout
    private var columnCount = 1

    // Called when the fragment is being created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the column count from the arguments if available
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    // Called to create the view hierarchy associated with the fragment
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // Check if the inflated view is a RecyclerView
        if (view is RecyclerView) {
            with(view) {
                // Set the layout manager based on the column count (number of columns)
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                // Start a new thread to fetch weather data
                thread {
                    // Try to build the URL and read the weather data as a JSON string
                    val weatherJSON = try {
                        buildURLForWeather()?.readText()
                    } catch (e: Exception) {
                        return@thread
                    }
                    // If weather data is successfully fetched
                    if (weatherJSON != null) {
                        // Parse the JSON string into a weather data object using Gson
                        val gson = Gson()
                        val weatherData = gson.fromJson<ExampleJson2KtKotlin>(
                            weatherJSON,
                            ExampleJson2KtKotlin::class.java
                        )

                        // Update the UI on the main thread
                        activity?.runOnUiThread {
                            //MUST PASS A
                            // Set the adapter for the RecyclerView with the fetched weather data
                            adapter = MyDailyForecastsRecyclerViewAdapter(weatherData.DailyForecasts)
                        }
                    }
                }
                // Uncomment the following line to use placeholder data instead
                // adapter = MyDailyForecastsRecyclerViewAdapter(PlaceholderContent.ITEMS)
            }
        }
        return view
    }

    companion object {
        // Argument name for the column count
        const val ARG_COLUMN_COUNT = "column-count"

        // Factory method to create a new instance of this fragment with the specified column count
        @JvmStatic
        fun newInstance(columnCount: Int) =
            DailyForecastsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}