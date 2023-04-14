package fr.epsi.mobile

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.projet_mobile_2_moussiron_lamothe.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import okhttp3.*
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Tab1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Tab3Fragment : Fragment() {

    lateinit var googleMap: GoogleMap

    @SuppressLint("MissingPermission")
    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                googleMap.isMyLocationEnabled = true
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
                googleMap.isMyLocationEnabled = true
            }
            else -> {
                // No location access granted.
            }
        }
    }

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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    fun getCityInfo(city: String, items: ArrayList<Magasins>): Magasins? {
        for (i in 0..items.size - 1) {
            val jsonCity = items.get(i)
            println(jsonCity)
            if(jsonCity.city == city){
                return jsonCity
            }
        }
        return null
    }

    val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val jsonCities = JSONObject((activity as HomeActivity).cities)
        val items = jsonCities.getJSONArray("cities")
        for (i in 0..items.length() - 1) {
            val jsonCity = items.getJSONObject(i)
            val city = MarkerOptions()
            val cityLatLng = LatLng(jsonCity.optDouble("lan", 0.0), jsonCity.optDouble("lng", 0.0))
            city.title(jsonCity.optString("city"))
            city.position(cityLatLng)
            googleMap.addMarker(city)
        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(48.854885, 2.338646), 5f))
        googleMap.setOnMapClickListener {
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        }
        googleMap.setOnInfoWindowClickListener {
            val magasin = getCityInfo(it.title.toString(), (activity as HomeActivity).magasins)
            val newIntent= Intent((activity as HomeActivity).application, DetailMagasinActivity::class.java)
            println("toto")

            newIntent.putExtra("name", magasin?.name)
            newIntent.putExtra("urlImage", magasin?.pictureStore)
            newIntent.putExtra("adresse", magasin?.address)
            newIntent.putExtra("city", magasin?.city)
            newIntent.putExtra("zipcode", magasin?.zipcode)
            newIntent.putExtra("description", magasin?.description)
            startActivity(newIntent)
        }
        this.googleMap = googleMap
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
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
            Tab3Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}