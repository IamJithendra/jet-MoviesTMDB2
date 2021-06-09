package com.karlis.moviestmdb

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.karlis.moviestmdb.databinding.ActivityFavoritesScreenBinding
import org.json.JSONException
import org.json.JSONObject
import java.math.RoundingMode


class FavoritesScreen : AppCompatActivity(), RecyclerAdapterList.OnItemClickListener {

    private var movList: ArrayList<MoviesDetails> = ArrayList()
    private lateinit var binding: ActivityFavoritesScreenBinding


    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showBackButton()

        movList = intent.getSerializableExtra("fromDB") as ArrayList<MoviesDetails>
        movList.reverse()
        Log.d("Favorites Screen", movList.toString())
        this.title = "Favorites (Items: ${movList.size})"
        binding.MoviesListView.isClickable = true
        binding.MoviesListView.adapter = MovieAdapterListView(this, movList)

        binding.MoviesListView.setOnItemClickListener { _, _, position, _ ->
            val clickedItem: MoviesDetails = movList[position]
            val intent = Intent(this, DetailScreen::class.java)
            intent.putExtra("movie_id", clickedItem.id)
            startActivity(intent)
        }

    }

    private fun showBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }


}