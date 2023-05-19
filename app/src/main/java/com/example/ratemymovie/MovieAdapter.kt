package com.example.ratemymovie

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
class MovieAdapter(var movieList: MovieWrapper?):
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewMovieName: TextView
        val textViewRating: TextView
        val layout: ConstraintLayout

        init {
            textViewMovieName = view.findViewById(R.id.textView_movie_name)
            textViewRating = view.findViewById(R.id.textView_movie_pg)
            layout = view.findViewById(R.id.layout_movie)

        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_movie_data, viewGroup, false)

        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList!!.results!!.get(position)

        holder.textViewMovieName.text = movie.name
        var context = holder.textViewMovieName.context
        holder.textViewMovieName.text = movie.name

        holder.layout.setOnClickListener {
            val loanDetailActivity = Intent(it.context, MovieDetailActivity::class.java)
            loanDetailActivity.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie)
            it.context.startActivity(loanDetailActivity)
        }
    }
    private fun deleteFromBackendless(position: Int, con : Context) {
        Backendless.Data.of(MovieData::class.java).remove(movieList!!.results!!.get(position),
            object : AsyncCallback<Long?> {
                override fun handleResponse(response: Long?) {
                    Toast.makeText(con, "${movieList!!.results!!.get(position).name} Deleted", Toast.LENGTH_SHORT).show()
                }

                override fun handleFault(fault: BackendlessFault?) {
                    if (fault != null) {
                        Log.d("Didn't work", "handleFault: ${fault.message}")
                    }
                }
            })
    }

    override fun getItemCount() = movieList!!.results!!.size
}