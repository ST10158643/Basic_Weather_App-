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
import za.co.varsitycollege.st10158643.basicweatherapp.placeholder.PlaceholderContent
import kotlin.concurrent.thread

/**
 * A fragment representing a list of Items.
 */
class DailyForecastsFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                thread {
                    val weatherJSON = try {
                        buildURLForWeather()?.readText()
                    } catch (e: Exception) {
                        return@thread
                    }
                    if (weatherJSON != null) {
                        val gson = Gson()
                        val weatherData = gson.fromJson<ExampleJson2KtKotlin>(
                            weatherJSON,
                            ExampleJson2KtKotlin::class.java
                        )

                        activity?.runOnUiThread {
                            adapter = MyDailyForecastsRecyclerViewAdapter(
                                weatherData.DailyForecasts
                            )
                        }

                    }
                }
               // adapter = MyDailyForecastsRecyclerViewAdapter(PlaceholderContent.ITEMS)
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
                DailyForecastsFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}