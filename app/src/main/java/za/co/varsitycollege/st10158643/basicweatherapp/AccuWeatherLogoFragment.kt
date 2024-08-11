package za.co.varsitycollege.st10158643.basicweatherapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccuWeatherLogoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccuWeatherLogoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    //Create the UI,to access components in the fragment layout/ view
    override fun onCreateView(
        // this view will contain the fragment layout components
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment (R.layout.fragment_accu_weather_logo)
        val fraggyView = inflater.inflate(R.layout.fragment_accu_weather_logo, container, false)

        // Find the ImageView in the inflated layout (R.layout.fragment_accu_weather_logo)
        val imageView = fraggyView.findViewById<ImageView>(R.id.iv_accuweather)

        // Add an event handler to the ImageView to open the AccuWeather website when clicked
        imageView?.setOnClickListener {
            // Create an Intent to open a web browser and navigate to the AccuWeather website
            val intent = Intent(
                Intent.ACTION_VIEW, // Action to view the specified data
                Uri.parse("http://www.accuweather.com/") // The URI to be opened
            )
            // Start the activity with the intent
            startActivity(intent)
        }

        // Return the inflated view to be displayed as the fragment's UI
        return fraggyView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccuWeatherLogoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccuWeatherLogoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    }