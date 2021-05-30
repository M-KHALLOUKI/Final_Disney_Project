package com.example.disney.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.disney.R
import com.example.disney.presentation.api.DisneyDetailResponse
import com.example.disney.presentation.Singletons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DisneyDetailFragment : Fragment() {

    private lateinit var textViewName: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_disney_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewName=view.findViewById(R.id.disney_detail_name)
        callApi()
    }

    private fun callApi() {
        val id=arguments?.getInt("disneyId")?:-1
        Singletons.disneyApi.getDisneyDetail(id).enqueue(object : Callback<DisneyDetailResponse> {
            override fun onResponse(
                call: Call<DisneyDetailResponse>,
                response: Response<DisneyDetailResponse>) {

                if(response.isSuccessful && response.body() !=null && id!=null) {
                    textViewName.text = response.body()!!.name

                }
            }

            override fun onFailure(call: Call<DisneyDetailResponse>, t: Throwable) {
            }

        })
    }
}