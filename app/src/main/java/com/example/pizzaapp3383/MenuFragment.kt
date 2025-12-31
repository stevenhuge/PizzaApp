package com.example.pizzaapp3383

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaapp3383.client.RetrofitClient
import com.example.pizzaapp3383.response.food.FoodResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val listMenu = ArrayList<FoodResponse>()

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
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtSearch: EditText = view.findViewById(R.id.editTextCari)

        val RVMenu: RecyclerView = view.findViewById(R.id.recyclerViewMenu)

        RVMenu.layoutManager = GridLayoutManager(activity, 2)
        RetrofitClient.instance.getFood().enqueue(
            object : Callback<ArrayList<FoodResponse>> {
                override fun onResponse(
                    call: Call<ArrayList<FoodResponse>>,
                    response: Response<ArrayList<FoodResponse>>
                ) {
                    listMenu.clear()
                    response.body()?.let { listMenu.addAll(it) }
                    var adapter = AdaptorMenu(listMenu)
                    RVMenu.adapter = adapter
                }
                override fun onFailure(p0: Call<ArrayList<FoodResponse>?>, p1: Throwable) {
                    TODO("Not yet implemented")
                }
            }
        )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MenuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}