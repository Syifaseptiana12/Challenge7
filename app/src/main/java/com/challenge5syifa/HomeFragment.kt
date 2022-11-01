package com.challenge5syifa

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.challenge5syifa.MainActivity.Companion.gson
import com.challenge5syifa.MainActivity.Companion.sharedPref
import com.challenge5syifa.adapter.MovieAdapter
import com.challenge5syifa.api.ClientRetrofit
import com.challenge5syifa.api.ClientRetrofit.key
import com.challenge5syifa.databinding.FragmentHomeBinding
import com.challenge5syifa.model.Movie
import com.challenge5syifa.model.MovieResponse
import com.challenge5syifa.room.entity.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private lateinit var bind:FragmentHomeBinding
    private lateinit var logged: User
    private lateinit var loadingDialog: AlertDialog
    private lateinit var adapter: MovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentHomeBinding.inflate(inflater,container,false)
        sharedPref.getString("logged", null)?.let {
           logged= gson.fromJson(it, User::class.java)
        }?: run {
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }
        loadingDialog = LoadingBuilder.loadingDialog(requireContext())
        adapter = MovieAdapter(object : MovieAdapter.Companion.MovieListener {
            override fun detail(movie: Movie) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailMovieFragment(
                    gson.toJson(movie)
                ))
            }
        })
        initElement()
        loadNote()
        return bind.root
    }
    private fun initElement(){
        bind.tvWelcome.text="Welcome, ${logged.username}"
        bind.btnProfile.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
        bind.rvMovie.layoutManager = LinearLayoutManager(requireContext())
        bind.rvMovie.adapter = adapter
    }

    private fun loadNote(){
        if(isNetworkAvailable(requireContext())) {
            loadingDialog.show()
            var listMovie = mutableListOf<Movie>()
            for (i in 1..ClientRetrofit.page) {
                val viewCall: Call<MovieResponse> = ClientRetrofit.apiService.getMovies(key, i)
                viewCall.enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.results?.let { listMovie.addAll(it) }
                        }
                        if (i == ClientRetrofit.page) {
                            adapter.setList(listMovie)
                            loadingDialog.dismiss()
                        }
                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        Toast.makeText(requireContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
        }else{
            Toast.makeText(requireContext(), "Tidak ada koneksi", Toast.LENGTH_SHORT)
                .show()
        }
    }
    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }
}