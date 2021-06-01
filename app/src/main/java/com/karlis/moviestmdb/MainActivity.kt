package com.karlis.moviestmdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView
import android.widget.SearchView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.karlis.moviestmdb.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject
import java.math.RoundingMode

class MainActivity : AppCompatActivity(), RecyclerAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var moviesList: ArrayList<Result>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moviesList = intent.getSerializableExtra("movies") as ArrayList<Result>
        val adapter = RecyclerAdapter(moviesList as List<Result>, this)
        binding.RecyclerView.adapter = adapter
        binding.RecyclerView.layoutManager = LinearLayoutManager(this)
        binding.RecyclerView.setHasFixedSize(true)
        setTitle()

    }

    private fun setTitle() {
        val numberOfMovies = intent.getStringExtra("moviesCount")
        this.title = "$numberOfMovies Popular Movies on TMDB"
    }

    override fun onItemClick(position: Int) {
        val clickedItem: Result = moviesList[position]
        val intent = Intent(this, DetailScreen::class.java)
        intent.putExtra("movie_id", clickedItem.id)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu?.findItem(R.id.mainMenuSearch)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchView.onActionViewExpanded()
            searchView.queryHint = "Search for Movies"
            searchView.focusSearch(View.FOCUS_UP)

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d("onQueryTextSubmit: ", query.toString())
                    loadMovies(query)
                    searchView.clearFocus()
                    searchItem.collapseActionView()
                    searchView.setQuery("",false)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.d("OnQueryTextChange: ", newText.toString())
                    return true
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun loadMovies(query: String?) {
        var listOfMovies: ArrayList<Result> = ArrayList()
        val apiKey = "73619d549f33ccdf0116452a1f3f9427"
        val urlString =
            "https://api.themoviedb.org/3/search/movie?api_key=$apiKey&language=en-US&query=$query&page=1&include_adult=false"

        val volleyStringRequest = StringRequest(
            Request.Method.GET,
            urlString,
            { response ->
                val resultJson = JSONObject(response)

                // Lambda 2
                val roundDouble: (String?) -> String = { number ->
                    number!!.toDouble().toBigDecimal().setScale(0, RoundingMode.UP).toDouble()
                        .toInt()
                        .toString()
                }
                val myMovie = MainMovies(
                    results = resultJson.getJSONArray("results")
                )
                val moviesTotal = myMovie.results.length()

                if (moviesTotal == 0) {
                    Toast.makeText(this, "No Results for \"${query}\"", Toast.LENGTH_LONG).show()
                    return@StringRequest
                }

                for (index in 0 until moviesTotal) {
                    // Lambda 1
                    val getStringFromJson: (String) -> String? = { item ->
                        try {
                            myMovie.results.getJSONObject(index).getString(item)
                        } catch (e: JSONException) {
                            println("Error")
                            "????"
                        }.toString()
                    }

                    val movie = Result(
                        title = getStringFromJson("title")!!,
                        poster_path = "https://image.tmdb.org/t/p/w500${getStringFromJson("poster_path")!!}",
                        vote_average = getStringFromJson("vote_average")!!.toDouble().toString(),
                        release_date = getStringFromJson("release_date")!!.take(4),
                        original_language = getStringFromJson("original_language")!!.uppercase(),
                        popularity = roundDouble(getStringFromJson("popularity")!!),
                        id = getStringFromJson("id")!!
                    )
                    Log.d("Movie Object:", movie.toString())
                    listOfMovies.add(movie)

                }

                val intent = Intent(this, SearchResults::class.java)
                intent.putExtra("Search", listOfMovies)
                intent.putExtra("Keyword", query)
                startActivity(intent)

            },
            { volleyError ->
                Log.d("Volley error msg: ", volleyError.toString())
            }
        )
        Volley.newRequestQueue(this).add(volleyStringRequest)
    }


}