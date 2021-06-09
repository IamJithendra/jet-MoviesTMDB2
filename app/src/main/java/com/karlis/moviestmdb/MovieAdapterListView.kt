package com.karlis.moviestmdb

import android.app.Activity
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.math.RoundingMode

class MovieAdapterListView(private val context: Activity, private val arrayList: ArrayList<MoviesDetails>): ArrayAdapter<MoviesDetails>(context, R.layout.movies_row_list_item, arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.movies_row_list_item, null)

        // Image
        val poster : ImageView = view.findViewById(R.id.MoviePosterImage)
        val title: TextView = view.findViewById(R.id.MovieTitle)
        val releaseDate: TextView = view.findViewById(R.id.MovieReleaseYear)
        val popularity: TextView = view.findViewById(R.id.MoviePopularity)
        val rating: TextView = view.findViewById(R.id.MovieVoteRating)
        val originalLanguage: TextView = view.findViewById(R.id.MovieOriginalLanguage)

        // Card
        val rowCard: CardView = view.findViewById(R.id.MoviesCardViewItem)
        val voteRatingCard: CardView = view.findViewById(R.id.CardViewMovieRating)
        val posterCard: CardView = view.findViewById(R.id.RowItemPosterCardView)
        val startRatingCard: CardView = view.findViewById(R.id.ratingStarsCardView)

        // Titles
        val popularityTitle: TextView = view.findViewById(R.id.PopularityTitle)
        val ratingTitle: TextView = view.findViewById(R.id.RatingTitle)


        Picasso
            .get()
            .load(arrayList[position].poster_path)
            .placeholder(R.drawable.ic_baseline_image_not_supported_24)
            .into(poster, object: com.squareup.picasso.Callback.EmptyCallback(){
                override fun onError(e: Exception?) {
                    super.onError(e)
                    Log.d("Picasso OnError : ", e.toString())
                    poster.scaleType = ImageView.ScaleType.FIT_CENTER

                }
            })

        // Text
        title.text = arrayList[position].title
        releaseDate.text = arrayList[position].release_date
        popularity.text = arrayList[position].popularity
        rating.text = arrayList[position].vote_average
        originalLanguage.text = arrayList[position].original_language

        // Star Images
        val star1: ImageView = view.findViewById(R.id.details_star1)
        val star2: ImageView = view.findViewById(R.id.details_star2)
        val star3: ImageView = view.findViewById(R.id.details_star3)
        val star4: ImageView = view.findViewById(R.id.details_star4)
        val star5: ImageView = view.findViewById(R.id.details_star5)

        // Animation

        rowCard.animation = AnimationUtils.loadAnimation(rowCard.context, R.anim.fade_in2)

        voteRatingCard.animation = AnimationUtils.loadAnimation(voteRatingCard.context, R.anim.rating_card_spin_animation)

        posterCard.animation = AnimationUtils.loadAnimation(posterCard.context, R.anim.fade_in)
        title.animation = AnimationUtils.loadAnimation(title.context, R.anim.fade_in)
        startRatingCard.animation = AnimationUtils.loadAnimation(startRatingCard.context, R.anim.fade_in)
        popularity.animation = AnimationUtils.loadAnimation(popularity.context, R.anim.fade_in)
        popularityTitle.animation = AnimationUtils.loadAnimation(popularityTitle.context, R.anim.fade_in)
        ratingTitle.animation = AnimationUtils.loadAnimation(ratingTitle.context, R.anim.fade_in)
        originalLanguage.animation = AnimationUtils.loadAnimation(originalLanguage.context, R.anim.fade_in)
        releaseDate.animation = AnimationUtils.loadAnimation(releaseDate.context, R.anim.fade_in)

        //Star Animation
        star1.animation = AnimationUtils.loadAnimation(star1.context, R.anim.blinking_star1)
        star2.animation = AnimationUtils.loadAnimation(star2.context, R.anim.blinking_star2)
        star3.animation = AnimationUtils.loadAnimation(star3.context, R.anim.blinking_star3)
        star4.animation = AnimationUtils.loadAnimation(star4.context, R.anim.blinking_star4)
        star5.animation = AnimationUtils.loadAnimation(star5.context, R.anim.blinking_star5)

        // Appearance
        rowCard.setCardBackgroundColor(Color.argb(0,104,121,128))
        voteRatingCard.setCardBackgroundColor(Color.argb(100, 91,138,114))
        posterCard.setCardBackgroundColor(Color.argb(15,104,121,128))
        startRatingCard.setCardBackgroundColor(Color.argb(0,104,121,128))

        // Double to Int
        val roundDouble: (String?) -> Int = { number ->
            number!!.toDouble().toBigDecimal().setScale(0, RoundingMode.FLOOR).toDouble()
                .toInt()
        }
        // Set Stars
        when (roundDouble(arrayList[position].vote_average)) {
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

        return view
    }
}