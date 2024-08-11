package za.co.varsitycollege.st10158643.basicweatherapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */

class MyDailyForecastsRecyclerViewAdapter(
    //PASS: List of DailyForecasts
    private val values: List<DailyForecasts>)
    //RETURN: RecyclerView.Adapter<MyDailyForecastsRecyclerViewAdapter.ViewHolder>
    : RecyclerView.Adapter<MyDailyForecastsRecyclerViewAdapter.ViewHolder>() {


    /* Called when RecyclerView needs a new ViewHolder of the given type to represent an item
     * onCreateViewHolder: function name. A method within the RecyclerView.Adapter class.
     * Method/ Fun called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     * PARAMETER 1:[parent: ViewGroup]Represents the parent view that the new ViewHolder will be attached to.
     * PARAMETER 2: [viewType: Int]Represents the type of view the ViewHolder will represent.
     * RETURN TYPE: [ViewHolder] fun will return instance of ViewHolder class*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the item layout (R.layout.fragment_item) and create a new ViewHolder
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    /* Called by RecyclerView to display the data at the specified position
     * PARAMETER 1: [holder: ViewHolder]Represents the ViewHolder that should be updated to represent
       the contents of the item at the given position in the data set.
     * PARAMETER 2: [position: Int]Represents the position of the item within the adapter's data set.*/
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //getting the item at the position
        val item = values[position]
        //setting the Adaptor data item to the view holder items

        holder.adapDate.text = item.Date?.substring(0,10)
        holder.adapMax.text = item.Temperature?.Minimum?.Value.toString()
        holder.adapMin.text = item.Temperature?.Maximum?.Value.toString()

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val adapDate: TextView = view.findViewById(R.id.tv_date)
        val adapMin: TextView = view.findViewById(R.id.tv_min)
        val adapMax: TextView = view.findViewById(R.id.tv_max)

        override fun toString() : String {
            return super.toString() + " '" + adapDate.text + "'"
        }

    }


}