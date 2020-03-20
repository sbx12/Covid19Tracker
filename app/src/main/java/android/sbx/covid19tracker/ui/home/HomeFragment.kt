package android.sbx.covid19tracker.ui.home
import kotlinx.android.synthetic.main.fragment_home.*
import android.os.Bundle
import android.sbx.covid19tracker.R
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        getData()
        return root
    }

    private fun getData(){
        val queue: RequestQueue = Volley.newRequestQueue(activity)

        val url = "https://corona.lmao.ninja/all"
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener {
                response->
                progress_circular_home.visibility = View.GONE
                try {
                    val jsonObject = JSONObject(response)
                    tvTotalConfirmed.text = jsonObject.getString("cases")
                    tvTotalDeaths.text = jsonObject.getString("deaths")
                    tvTotalRecovered.text = jsonObject.getString("recovered")
                } catch (e : JSONException){
                    e.printStackTrace()
                }

        }, Response.ErrorListener {
                Log.v("oyeah", "efewfewfewfewrfewr")
                progress_circular_home.visibility = View.GONE
        })
        queue.add(stringRequest)
    }
}