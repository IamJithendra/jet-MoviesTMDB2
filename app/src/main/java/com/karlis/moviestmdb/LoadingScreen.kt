package com.karlis.moviestmdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.karlis.moviestmdb.utils.Constants.Constants.Companion.API_KEY
import org.json.JSONException
import org.json.JSONObject
import java.math.RoundingMode

class LoadingScreen : AppCompatActivity() {

    private var movieList: ArrayList<MoviesDetails> = ArrayList()
    private lateinit var movie: MoviesDetails
    private var numberOfMovies: String = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_screen)


        for (i in 1 .. 5){
            loadMovies(i)
        }
//        loadMovies(1)

        loadAnimations()
        loadRecycleView()

    }


    private fun loadRecycleView() {
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("movies", movieList)
            intent.putExtra("moviesCount", numberOfMovies)
            startActivity(intent)
            finish()
        }, 4500)
    }

    private fun loadMovies(page: Int) {
            val urlString = "https://api.themoviedb.org/3/movie/popular?api_key=$API_KEY&page=$page"
            val volleyStringRequest = StringRequest(
                Request.Method.GET,
                urlString,
                { response ->
                    // Number of Pages
                    val numberOfPages = JSONObject(response).getString("total_pages").toInt()

                    // Get Movie Detail
                    val getItemFromJsonArray: (String, Int) -> String = { key, index ->
                        try {
                            JSONObject(response).getJSONArray("results").getJSONObject(index).getString(key)
                        } catch (e: JSONException) {
                            println(e.message)
                            "????"
                        }
                    }

                    // Nuumber of Movies in One Page
                    val numberOfMoviesInPage = JSONObject(response).getJSONArray("results").length()

                    // Round number
                    val roundNumber: (String) -> String = {
                        it.toDouble().toBigDecimal().setScale(0, RoundingMode.UP).toDouble().toInt()
                            .toString()
                    }


                    for (i in 0 until numberOfMoviesInPage){
                        // To Object
                        movie = MoviesDetails(
                            id = getItemFromJsonArray("id",i),
                            original_language = getItemFromJsonArray("original_language",i).uppercase(),
                            popularity = roundNumber(getItemFromJsonArray("popularity",i)),
                            poster_path = "https://image.tmdb.org/t/p/w500${getItemFromJsonArray("poster_path",i)}",
                            release_date = getItemFromJsonArray("release_date",i).take(4),
                            title = getItemFromJsonArray("title",i),
                            vote_average = getItemFromJsonArray("vote_average",i).toDouble().toString()
                        )
                        movieList.add(movie)
                        numberOfMovies = movieList.size.toString()
                    }

                },
                { volleyError ->
                    Log.d("Volley error msg: ", volleyError.toString())
                }
            )
            Volley.newRequestQueue(this).add(volleyStringRequest)
    }

    private fun loadAnimations() {
        val logo: ImageView = findViewById(R.id.LaunchScreenLogo)
        logo.animation = AnimationUtils.loadAnimation(logo.context, R.anim.launch_screen_animation)


        val credentials: TextView = findViewById(R.id.credentials)
        credentials.animation = AnimationUtils.loadAnimation(credentials.context, R.anim.fade_in)

        val startProgressBar: ProgressBar = findViewById(R.id.launchScreenProgressBar)
        startProgressBar.animation = AnimationUtils.loadAnimation(startProgressBar.context, R.anim.change_alpha)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }


}