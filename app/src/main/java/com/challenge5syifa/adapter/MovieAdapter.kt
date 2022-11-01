package com.challenge5syifa.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.challenge5syifa.databinding.RowMovieBinding
import com.challenge5syifa.model.Movie

class MovieAdapter(private val listener: MovieListener)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var list = listOf<Movie>()
    inner class ViewHolder(val bind: RowMovieBinding)
        : RecyclerView.ViewHolder(bind.root)
    fun setList(list:List<Movie>){
        this.list=list;
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val bind = RowMovieBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = list[position]
        with(viewHolder) {
            bind.tvJudul.text = item.judul
            bind.tvDeskripsi.text = item.deskripsi
            Glide.with(viewHolder.itemView.context)
                .load("https://image.tmdb.org/t/p/w185" + item.foto)
                .into(bind.ivFoto)
            bind.root.setOnClickListener{
                listener.detail(item)
            }
        }
    }

    override fun getItemCount() = list.size

    companion object{
        interface MovieListener{
            fun detail(movie:Movie)
        }
    }
}