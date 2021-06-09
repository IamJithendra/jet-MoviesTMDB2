package com.karlis.moviestmdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.karlis.moviestmdb.databinding.ActivitySearchResultsBinding

class SearchResults : AppCompatActivity(), RecyclerAdapterList.OnItemClickListener {

    private lateinit var binding: ActivitySearchResultsBinding
    private lateinit var moviesDetailsList: ArrayList<MoviesDetails>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showBackButton()
        setTitleForSearchItem()
        getMovieListSearchResults()
        setUpRecyclerView()
    }

    private fun showBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setTitleForSearchItem() {
        val searchKey = intent.getStringExtra("Keyword")
        this.title = "Search results for: \"${searchKey}\""
    }

    private fun getMovieListSearchResults() {
        moviesDetailsList = intent.getSerializableExtra("Search") as ArrayList<MoviesDetails>
    }

    private fun setUpRecyclerView() {
        val adapter = RecyclerAdapterList(moviesDetailsList, this)
        binding.MainRecyclerView.adapter = adapter
        binding.MainRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.MainRecyclerView.setHasFixedSize(true)
    }

    override fun onItemClick(position: Int) {
        val clickedItem: MoviesDetails = moviesDetailsList[position]
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