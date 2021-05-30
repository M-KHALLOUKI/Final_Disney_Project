package com.example.disney.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.disney.R
import com.example.disney.presentation.Singletons
import com.example.disney.presentation.api.DisneyListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DisneyListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private val adapter=DisneyAdapter(listOf(), ::onClickedDisney)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_disney_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView=view.findViewById(R.id.disney_recyclerview)

        recyclerView.apply{
            layoutManager=LinearLayoutManager(context)
            adapter=this@DisneyListFragment.adapter
        }



        Singletons.disneyApi.getDisneyList(2).enqueue(object : Callback<DisneyListResponse>{
            override fun onResponse(call: Call<DisneyListResponse>, response: Response<DisneyListResponse>
            ) {
                if(response.isSuccessful && response.body() !=null){
                    val disneyResponse: DisneyListResponse=response.body()!!
                    adapter.updateList(disneyResponse.data)
                }
            }

            override fun onFailure(call: Call<DisneyListResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })



    }
    private fun onClickedDisney(id: Int) {
        findNavController().navigate(R.id.navigateToDisneyDetailFragment, bundleOf(
            "disneyId" to (id+138)

        ))
    }
}
