package android.sbx.covid19tracker.ui.country

import android.sbx.covid19tracker.R
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class CovidCountryAdapter(private var covidCountries: ArrayList<CovidCountry>) :
    RecyclerView.Adapter<CovidCountryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_covid_country, parent, false)
        Log.d("Error", "eFrom Adaapter1")
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val case: CovidCountry = covidCountries[position]
        holder.tvTotalCases.text = case.mCases
        holder.tvCountryName.text = case.mCovidCountry
        Log.d("Error", "eFrom Adaapter3")
    }

    override fun getItemCount(): Int {
        return covidCountries.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvTotalCases: TextView = itemView.findViewById(R.id.tvTotalCases)
        var tvCountryName: TextView = itemView.findViewById(R.id.tvCountryName)
    }

}