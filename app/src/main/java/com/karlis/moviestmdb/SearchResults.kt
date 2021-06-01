package com.karlis.moviestmdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.karlis.moviestmdb.databinding.ActivitySearchResultsBinding

class SearchResults : AppCompatActivity(), RecyclerAdapter.OnItemClickListener {

    private lateinit var binding: ActivitySearchResultsBinding
    private lateinit var moviesList: ArrayList<Result>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val searchKey = intent.getStringExtra("Keyword")

        this.title = "Search results for: \"${searchKey}\""

        moviesList = intent.getSerializableExtra("Search") as ArrayList<Result>
        Log.d("Movies List --..", moviesList.toString())
        val adapter = RecyclerAdapter(moviesList as List<Result>, this)
        binding.RecyclerView.adapter = adapter
        binding.RecyclerView.layoutManager = LinearLayoutManager(this)
        binding.RecyclerView.setHasFixedSize(true)

    }

    override fun onItemClick(position: Int) {
        val clickedItem: Result = moviesList[position]
        val intent = Intent(this, DetailScreen::class.java)
        intent.putExtra("movie_id", clickedItem.id)
        startActivity(intent)
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
}