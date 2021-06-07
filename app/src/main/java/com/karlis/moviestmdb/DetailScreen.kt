package com.karlis.moviestmdb

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AndroidRuntimeException
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.karlis.moviestmdb.databinding.ActivityDetailScreenBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject
import java.math.RoundingMode
import kotlin.properties.Delegates

class DetailScreen : AppCompatActivity() {
    private lateinit var binding: ActivityDetailScreenBinding
    private lateinit var movieId: String
    private var videoTrailerId: String? = null

    // Card
    private lateinit var backdropCard: CardView
    private lateinit var infoCard: CardView
    private lateinit var ratingCard: CardView
    private lateinit var statusCard: CardView
    private lateinit var starCard: CardView

    // Stars
    private lateinit var star1: ImageView
    private lateinit var star2: ImageView
    private lateinit var star3: ImageView
    private lateinit var star4: ImageView
    private lateinit var star5: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showBackButton()
        getMovie()
        bindCards()
        bindStars()
        animateViews()
        changeAppearance()
        starAnimation()



    }

    private fun getMovie() {
        movieId = intent.getStringExtra("movie_id").toString()
        loadMovieDescription(id = movieId)
    }

    private fun showBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun bindStars() {
        star1 = binding.detailsStar1
        star2 = binding.detailsStar2
        star3 = binding.detailsStar3
        star4 = binding.detailsStar4
        star5 = binding.detailsStar5
    }

    private fun setStarImages(rating: String){
        // Set Stars
        val roundDouble: (String?) -> Int = { number ->
            number!!.toDouble().toBigDecimal().setScale(0, RoundingMode.FLOOR).toDouble()
                .toInt()
        }

        when (roundDouble(rating)) {
            0 -> {
                star1.setImageResource(R.drawable.ic_baseline_star_border_24)
                star2.setImageResource(R.drawable.ic_baseline_star_border_24)
                star3.setImageResource(R.drawable.ic_baseline_star_border_24)
                star4.setImageResource(R.drawable.ic_baseline_star_border_24)
                star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
            1 -> {
                star1.setImageResource(R.drawable.ic_baseline_star_half_24)
                star2.setImageResource(R.drawable.ic_baseline_star_border_24)
                star3.setImageResource(R.drawable.ic_baseline_star_border_24)
                star4.setImageResource(R.drawable.ic_baseline_star_border_24)
                star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
            2 -> {
                star1.setImageResource(R.drawable.ic_baseline_star_24)
                star2.setImageResource(R.drawable.ic_baseline_star_border_24)
                star3.setImageResource(R.drawable.ic_baseline_star_border_24)
                star4.setImageResource(R.drawable.ic_baseline_star_border_24)
                star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
            3 -> {
                star1.setImageResource(R.drawable.ic_baseline_star_24)
                star2.setImageResource(R.drawable.ic_baseline_star_half_24)
                star3.setImageResource(R.drawable.ic_baseline_star_border_24)
                star4.setImageResource(R.drawable.ic_baseline_star_border_24)
                star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
            4 -> {
                star1.setImageResource(R.drawable.ic_baseline_star_24)
                star2.setImageResource(R.drawable.ic_baseline_star_24)
                star3.setImageResource(R.drawable.ic_baseline_star_border_24)
                star4.setImageResource(R.drawable.ic_baseline_star_border_24)
                star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
            5 -> {
                star1.setImageResource(R.drawable.ic_baseline_star_24)
                star2.setImageResource(R.drawable.ic_baseline_star_24)
                star3.setImageResource(R.drawable.ic_baseline_star_half_24)
                star4.setImageResource(R.drawable.ic_baseline_star_border_24)
                star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
            6 -> {
                star1.setImageResource(R.drawable.ic_baseline_star_24)
                star2.setImageResource(R.drawable.ic_baseline_star_24)
                star3.setImageResource(R.drawable.ic_baseline_star_24)
                star4.setImageResource(R.drawable.ic_baseline_star_border_24)
                star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
            7 -> {
                star1.setImageResource(R.drawable.ic_baseline_star_24)
                star2.setImageResource(R.drawable.ic_baseline_star_24)
                star3.setImageResource(R.drawable.ic_baseline_star_24)
                star4.setImageResource(R.drawable.ic_baseline_star_half_24)
                star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
            8 -> {
                star1.setImageResource(R.drawable.ic_baseline_star_24)
                star2.setImageResource(R.drawable.ic_baseline_star_24)
                star3.setImageResource(R.drawable.ic_baseline_star_24)
                star4.setImageResource(R.drawable.ic_baseline_star_24)
                star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
            9 -> {
                star1.setImageResource(R.drawable.ic_baseline_star_24)
                star2.setImageResource(R.drawable.ic_baseline_star_24)
                star3.setImageResource(R.drawable.ic_baseline_star_24)
                star4.setImageResource(R.drawable.ic_baseline_star_24)
                star5.setImageResource(R.drawable.ic_baseline_star_half_24)
            }
            10 -> {
                star1.setImageResource(R.drawable.ic_baseline_star_24)
                star2.setImageResource(R.drawable.ic_baseline_star_24)
                star3.setImageResource(R.drawable.ic_baseline_star_24)
                star4.setImageResource(R.drawable.ic_baseline_star_24)
                star5.setImageResource(R.drawable.ic_baseline_star_24)
            }
        }
    }

    private fun starAnimation(){
            star1.animation = AnimationUtils.loadAnimation(star1.context, R.anim.blinking_star1)
            star2.animation = AnimationUtils.loadAnimation(star2.context, R.anim.blinking_star2)
            star3.animation = AnimationUtils.loadAnimation(star3.context, R.anim.blinking_star3)
            star4.animation = AnimationUtils.loadAnimation(star4.context, R.anim.blinking_star4)
            star5.animation = AnimationUtils.loadAnimation(star5.context, R.anim.blinking_star5)
    }

    private fun bindCards() {
        backdropCard = binding.DetailBackdropPathCardView
        infoCard = binding.DetailInfoCardView
        ratingCard = binding.DetailVoteAverageCardView
        statusCard = binding.DetailStatusCardView
        starCard = binding.detailsRatingStarsCardView    }

    private fun changeAppearance() {
        infoCard.setCardBackgroundColor(Color.TRANSPARENT)
        starCard.setCardBackgroundColor(Color.TRANSPARENT)
        statusCard.setCardBackgroundColor(Color.argb(150, 91,138,114))
        ratingCard.setCardBackgroundColor(Color.argb(150, 91,138,114))
    }

    private fun animateViews() {
        backdropCard.animation = AnimationUtils.loadAnimation(backdropCard.context, R.anim.detail_backdrop_animation)
        infoCard.animation = AnimationUtils.loadAnimation(infoCard.context, R.anim.detail_info_animation)
        ratingCard.animation = AnimationUtils.loadAnimation(ratingCard.context, R.anim.rating_card_spin_animation)
    }


    @SuppressLint("ResourceType")
    private fun loadMovieDescription(id: String) {
        val apiKey = "73619d549f33ccdf0116452a1f3f9427"
        val appendToResponse = "&append_to_response=videos"
        val urlString = "https://api.themoviedb.org/3/movie/$id?api_key=$apiKey$appendToResponse"
        val volleyStringRequest = StringRequest(
            Request.Method.GET,
            urlString,
            { response ->
                val resultJson = JSONObject(response)

                //Lambda 1
                val getItem: (String) -> String? = {
                    val numberOfGenres = resultJson.getJSONArray(it).length()
                    val listOfGenres: ArrayList<String> = ArrayList()
                    var counter = 1
                    for (genreIndex in 0 until numberOfGenres) {
                        val newGenre = resultJson.getJSONArray(it).getJSONObject(genreIndex)
                            .getString("name")
                        listOfGenres.add("$counter) $newGenre")
                        counter += 1
                    }
                    listOfGenres.joinToString("\n")
                }

                // Lambda 2
                val getStringFromJson: (String) -> String? = {
                    resultJson.getString(it)
                }

                this.title = getStringFromJson("title") // Action Bar Title

                // textView
                binding.DetailTitle.text = getStringFromJson("title")
                binding.DetailStatus.text = getStringFromJson("status")
                binding.DetailReleaseDate.text = getStringFromJson("release_date")
                binding.DetailOriginalLanguage.text = getStringFromJson("original_language")?.uppercase()
                binding.DetailVoteAverage.text = getStringFromJson("vote_average")?.toDouble().toString()
                binding.DetailGenre.text = getItem("genres")
                binding.DetailOverviewText.text = getStringFromJson("overview")
                binding.DetailTagline.text = getStringFromJson("tagline")
                binding.DetailProductionCompanies.text = getItem("production_companies")
                binding.DetailProductionCountries.text = getItem("production_countries")

                // Rating Stars
                setStarImages(getStringFromJson("vote_average")!!)

                // Set Visibility
                val isEmptyCheck: (String) -> Boolean = {
                    getStringFromJson(it) != ""
                }

                val isEmptyCheck2: (String) -> Boolean = {
                    getItem(it) != ""
                }

                binding.DetailOverviewText.isVisible = isEmptyCheck("overview")
                binding.DetailOverviewTitle.isVisible = isEmptyCheck("overview")

                binding.DetailTagline.isVisible = isEmptyCheck("tagline")

                binding.DetailReleaseDateTitle.isVisible = isEmptyCheck("release_date")
                binding.DetailReleaseDate.isVisible = isEmptyCheck("release_date")

                binding.DetailProductionCompanies.isVisible = isEmptyCheck2("production_companies")
                binding.DetailProductionCompaniesTitle.isVisible = isEmptyCheck2("production_companies")

                binding.DetailProductionCountries.isVisible = isEmptyCheck2("production_countries")
                binding.DetailProductionCountriesTitle.isVisible = isEmptyCheck2("production_countries")

                // GET YouTube Video ID
                try {
                    videoTrailerId = resultJson.getJSONObject("videos").getJSONArray("results").getJSONObject(0).getString("key").toString()
                } catch (error: Exception) {
                    Log.d("Video Error", error.message.toString())
                }

                // Image
                loadImage(imagePath = resultJson.getString("poster_path"), inTo = binding.DetailPoster)
                loadImage(imagePath = resultJson.getString("backdrop_path"), inTo = binding.DetailBackdropPath)

                // Appearance
                binding.DetailPosterCardView.setCardBackgroundColor(Color.argb(15,104,121,128))
                binding.DetailBackdropPathCardView.setCardBackgroundColor(Color.argb(15,104,121,128))

            },
            { volleyError ->
                Log.d("Volley error msg: ", volleyError.toString())
            }
        )
        Volley.newRequestQueue(this).add(volleyStringRequest)
    }

    private fun openUrl(movieID: String?=null, videoTrailerId: String?=null, page: String?) {
        /*
        * Opel Url from specific id
        * movieId (Optional) - Default (null)
        * videoTrailerId (Optional) - Default (null)
        */
        try {
            val openURL = Intent(Intent.ACTION_VIEW)
            when(page){
                "TMDB" -> {
                    val tmdbHomepage = "https://www.themoviedb.org/movie/$movieID"
                    openURL.data = Uri.parse(tmdbHomepage)
                }
                "YouTube" -> {
                    val youTubeLink = "https://www.youtube.com/watch?v=$videoTrailerId"
                    openURL.data = Uri.parse(youTubeLink)
                }
            }

            Toast.makeText(this, "Redirecting you to $page", Toast.LENGTH_SHORT)
                .show()

            startActivity(openURL)
        } catch (error: ActivityNotFoundException) {
            Log.d("Error Message:", error.message.toString())
        }
    }

    private fun loadImage(imagePath: String, inTo: ImageView) {
        /*
        * Load Image to specific Location
        */
        Picasso
            .get()
            .load("https://image.tmdb.org/t/p/w500$imagePath")
            .placeholder(R.drawable.ic_baseline_image_not_supported_24)
            .into(inTo, object : com.squareup.picasso.Callback.EmptyCallback() {
                override fun onSuccess() {
                    super.onSuccess()
                    binding.DetailBackdropPathProgressBar.isVisible = false
                }
                override fun onError(e: java.lang.Exception?) {
                    super.onError(e)
                    Log.d("OnError : ", "Error Picassa Image Load")
                    inTo.scaleType = ImageView.ScaleType.FIT_CENTER
                    binding.DetailBackdropPathProgressBar.isVisible = false
                }
            })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("BUTTON", item.toString())

        when (item.toString()){
            "Homepage" -> openUrl(movieID = this.movieId, page = "TMDB")
            "YouTube" -> {
                try {
                    openUrl(videoTrailerId = this.videoTrailerId, page = "YouTube")
                } catch (e: AndroidRuntimeException){
                    Log.d("Youtube link error:", e.message.toString())
                }
            }
            else -> {
                when (item.itemId) {
                    android.R.id.home -> {
                        finish()
                        return true
                    }
                }
            }

        }
        return super.onOptionsItemSelected(item)
    }

}