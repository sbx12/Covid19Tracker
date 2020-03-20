package android.sbx.covid19tracker.ui.country

import android.os.Bundle
import android.sbx.covid19tracker.R
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_country.*
import org.json.JSONArray
import org.json.JSONException

class CountryFragment : Fragment() {

    // lateinit var rvCovidCountry : RecyclerView
    //  lateinit var  progressBar: ProgressBar
    private var covidCountries = ArrayList<CovidCountry>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_country, container, false)
        getDataFromServer()
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvCovidCountry.layoutManager = LinearLayoutManager(context)
    }

    private fun showRecyclerView() {
        val covidCountryAdapter = CovidCountryAdapter(covidCountries)
        rvCovidCountry.adapter = covidCountryAdapter
    }

    private fun getDataFromServer() {
        val url = "https://corona.lmao.ninja/countries"
        covidCountries = ArrayList()

        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener { response ->
            progress_circular_country.visibility = View.GONE
            try {
                var jsonArray = JSONArray(response)
                for (i in 0 until jsonArray.length()) {
                    var data = jsonArray.getJSONObject(i)
                    covidCountries.add(
                        CovidCountry(
                            data.getString("country"),
                            data.getString("cases")
                        )
                    )
                    //Log.d("Error", "$covidCountries[i]")
                }
                showRecyclerView()
            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }, Response.ErrorListener {
            progress_circular_country.visibility = View.GONE
            Log.d("Error", "Could Not connect!!!!")
        })
        Volley.newRequestQueue(activity).add(stringRequest)
    }

}