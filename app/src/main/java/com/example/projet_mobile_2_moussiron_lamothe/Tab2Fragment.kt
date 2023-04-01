package fr.epsi.mobile

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet_mobile_2_moussiron_lamothe.Categories
import com.example.projet_mobile_2_moussiron_lamothe.CategoriesAdapter
import com.example.projet_mobile_2_moussiron_lamothe.HomeActivity
import com.example.projet_mobile_2_moussiron_lamothe.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Tab2Fragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categories = arrayListOf<Categories>()

        val recyclerviewCategorie = view.findViewById<RecyclerView>(R.id.recyclerviewProduit)
        recyclerviewCategorie.layoutManager = LinearLayoutManager(view.context)
        val categoriesAdapter = CategoriesAdapter(categories)
        recyclerviewCategorie.adapter = categoriesAdapter

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestUrl = "https://www.ugarit.online/epsi/offers.json"
        val request =
            Request.Builder().url(mRequestUrl).cacheControl(CacheControl.FORCE_NETWORK).build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: okhttp3.Call, response: Response) {
                val data = response.body?.string()
                if (data != null) {
                    val jsCategories = JSONObject(data)
                    val jsArrayCategorie = jsCategories.getJSONArray("items")

                    for (i in 0 until jsArrayCategorie.length()) {
                        val jsCategorie = jsArrayCategorie.getJSONObject(i)
                        val categorie = Categories(
                            jsCategorie.optString("name", "Not found"),
                            jsCategorie.optString("description", "Not found"),
                            jsCategorie.optString("picture_url", "Not found")
                        )
                        categories.add(categorie)
                    }
                    activity?.runOnUiThread(Runnable {
                        categoriesAdapter.notifyDataSetChanged()
                    })

                    Log.e("WS", data)
                }
            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                activity?.runOnUiThread(Runnable {
                    Toast.makeText(activity!!.application, e.message, Toast.LENGTH_SHORT).show()
                })
            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Tab1Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Tab2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
