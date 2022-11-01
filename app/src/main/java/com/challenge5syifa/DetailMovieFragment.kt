package com.challenge5syifa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.challenge5syifa.databinding.FragmentDetailMovieBinding
import com.challenge5syifa.model.Movie

class DetailMovieFragment : Fragment() {
    private lateinit var bind: FragmentDetailMovieBinding
    private val args: DetailMovieFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentDetailMovieBinding.inflate(inflater, container, false)
        val movie = MainActivity.gson.fromJson(args.movie, Movie::class.java)
        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w185" + movie.foto)
            .into(bind.ivFoto)
        bind.tvJudul.text=movie.judul
        bind.tvTanggal.text=movie.releaseDate
        bind.tvDeskripsi.text=movie.deskripsi
        bind.tvPopularitas.text=movie.popularity
        bind.tvVote.text="${movie.voteAverage}/100"
        return bind.root
    }
}