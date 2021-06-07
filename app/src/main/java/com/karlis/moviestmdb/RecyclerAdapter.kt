package com.karlis.moviestmdb

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AbsListView
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.math.RoundingMode

class RecyclerAdapter(private val movies: ArrayList<MoviesDetails>, private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movies_row_item, parent, false)
        return MovieViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: RecyclerAdapter.MovieViewHolder, position: Int) {
        val currentItem = movies[position]



        // Image
        Picasso
            .get()
            .load(currentItem.poster_path)
            .placeholder(R.drawable.ic_baseline_image_not_supported_24)
            .into(holder.moviePosterPicture, object: com.squareup.picasso.Callback.EmptyCallback(){
                override fun onSuccess() {
                    super.onSuccess()
//                    Log.d("Picasso OnSuccess : ", "Successful Image Load")
                }
                override fun onError(e: Exception?) {
                    super.onError(e)
                    Log.d("Picasso OnError : ", e.toString())
                    holder.moviePosterPicture.scaleType = ImageView.ScaleType.FIT_CENTER

                }
            })

        // Text
        holder.movieTitle.text = currentItem.title
        holder.moviePopularity.text = currentItem.popularity
        holder.movieOriginalLanguage.text = currentItem.original_language
        holder.movieVoteRating.text = currentItem.vote_average
        holder.movieReleaseYear.text = currentItem.release_date



        // Animation
        holder.posterCard.animation = AnimationUtils.loadAnimation(holder.posterCard.context, R.anim.fade_in)
        holder.movieTitle.animation = AnimationUtils.loadAnimation(holder.movieTitle.context, R.anim.fade_in)
        holder.startRatingCard.animation = AnimationUtils.loadAnimation(holder.startRatingCard.context, R.anim.fade_in)
        holder.moviePopularity.animation = AnimationUtils.loadAnimation(holder.moviePopularity.context, R.anim.fade_in)
        holder.popularityTitle.animation = AnimationUtils.loadAnimation(holder.popularityTitle.context, R.anim.fade_in)
        holder.ratingTitle.animation = AnimationUtils.loadAnimation(holder.ratingTitle.context, R.anim.fade_in)
        holder.movieOriginalLanguage.animation = AnimationUtils.loadAnimation(holder.movieOriginalLanguage.context, R.anim.fade_in)
        holder.movieReleaseYear.animation = AnimationUtils.loadAnimation(holder.movieReleaseYear.context, R.anim.fade_in)


        if (position % 2 == 0) {
            holder.rowCard.animation = AnimationUtils.loadAnimation(holder.rowCard.context, R.anim.row_item_animation_1)
        } else{
            holder.rowCard.animation = AnimationUtils.loadAnimation(holder.rowCard.context, R.anim.row_item_animation_2)
        }

        holder.voteRatingCard.animation = AnimationUtils.loadAnimation(holder.voteRatingCard.context, R.anim.rating_card_spin_animation)

        //Star Animation
        holder.star1.animation = AnimationUtils.loadAnimation(holder.star1.context, R.anim.blinking_star1)
        holder.star2.animation = AnimationUtils.loadAnimation(holder.star2.context, R.anim.blinking_star2)
        holder.star3.animation = AnimationUtils.loadAnimation(holder.star3.context, R.anim.blinking_star3)
        holder.star4.animation = AnimationUtils.loadAnimation(holder.star4.context, R.anim.blinking_star4)
        holder.star5.animation = AnimationUtils.loadAnimation(holder.star5.context, R.anim.blinking_star5)

        // Appearance
        holder.rowCard.setCardBackgroundColor(Color.argb(15,104,121,128))
        holder.voteRatingCard.setCardBackgroundColor(Color.argb(100, 91,138,114))
        holder.posterCard.setCardBackgroundColor(Color.argb(15,104,121,128))
        holder.startRatingCard.setCardBackgroundColor(Color.argb(0,104,121,128))

        // Double to Int
        val roundDouble: (String?) -> Int = { number ->
            number!!.toDouble().toBigDecimal().setScale(0, RoundingMode.FLOOR).toDouble()
                .toInt()
        }
        // Set Stars
        when (roundDouble(currentItem.vote_average)) {
            0 -> {
                holder.star1.setImageResource(R.drawable.ic_baseline_star_border_24)
                holder.star2.setImageResource(R.drawable.ic_baseline_star_border_24)
                holder.star3.setImageResource(R.drawable.ic_baseline_star_border_24)
                holder.star4.setImageResource(R.drawable.ic_baseline_star_border_24)
                holder.star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
            1 -> {
                holder.star1.setImageResource(R.drawable.ic_baseline_star_half_24)
                holder.star2.setImageResource(R.drawable.ic_baseline_star_border_24)
                holder.star3.setImageResource(R.drawable.ic_baseline_star_border_24)
                holder.star4.setImageResource(R.drawable.ic_baseline_star_border_24)
                holder.star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
            2 -> {
                holder.star1.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star2.setImageResource(R.drawable.ic_baseline_star_border_24)
                holder.star3.setImageResource(R.drawable.ic_baseline_star_border_24)
                holder.star4.setImageResource(R.drawable.ic_baseline_star_border_24)
                holder.star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
            3 -> {
                holder.star1.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star2.setImageResource(R.drawable.ic_baseline_star_half_24)
                holder.star3.setImageResource(R.drawable.ic_baseline_star_border_24)
                holder.star4.setImageResource(R.drawable.ic_baseline_star_border_24)
                holder.star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
            4 -> {
                holder.star1.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star2.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star3.setImageResource(R.drawable.ic_baseline_star_border_24)
                holder.star4.setImageResource(R.drawable.ic_baseline_star_border_24)
                holder.star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
            5 -> {
                holder.star1.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star2.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star3.setImageResource(R.drawable.ic_baseline_star_half_24)
                holder.star4.setImageResource(R.drawable.ic_baseline_star_border_24)
                holder.star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
            6 -> {
                holder.star1.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star2.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star3.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star4.setImageResource(R.drawable.ic_baseline_star_border_24)
                holder.star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
            7 -> {
                holder.star1.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star2.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star3.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star4.setImageResource(R.drawable.ic_baseline_star_half_24)
                holder.star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
            8 -> {
                holder.star1.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star2.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star3.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star4.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
            9 -> {
                holder.star1.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star2.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star3.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star4.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star5.setImageResource(R.drawable.ic_baseline_star_half_24)
            }
            10 -> {
                holder.star1.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star2.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star3.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star4.setImageResource(R.drawable.ic_baseline_star_24)
                holder.star5.setImageResource(R.drawable.ic_baseline_star_24)
            }
        }

    }

    override fun getItemCount() = movies.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        // Image
        val moviePosterPicture: ImageView = itemView.findViewById(R.id.MoviePosterImage)
        // Text
        val movieTitle: TextView = itemView.findViewById(R.id.MovieTitle)
        val moviePopularity: TextView = itemView.findViewById(R.id.MoviePopularity)
        val movieOriginalLanguage: TextView = itemView.findViewById(R.id.MovieOriginalLanguage)
        val movieVoteRating: TextView = itemView.findViewById(R.id.MovieVoteRating)
        val movieReleaseYear: TextView = itemView.findViewById(R.id.MovieReleaseYear)

        //
        val popularityTitle: TextView = itemView.findViewById(R.id.PopularityTitle)
        val ratingTitle: TextView = itemView.findViewById(R.id.RatingTitle)

        // Card
        val rowCard: CardView = itemView.findViewById(R.id.MoviesCardViewItem)
        val voteRatingCard: CardView = itemView.findViewById(R.id.CardViewMovieRating)
        val posterCard: CardView = itemView.findViewById(R.id.RowItemPosterCardView)
        val startRatingCard: CardView = itemView.findViewById(R.id.ratingStarsCardView)

        // Star Images
        val star1: ImageView = itemView.findViewById(R.id.details_star1)
        val star2: ImageView = itemView.findViewById(R.id.details_star2)
        val star3: ImageView = itemView.findViewById(R.id.details_star3)
        val star4: ImageView = itemView.findViewById(R.id.details_star4)
        val star5: ImageView = itemView.findViewById(R.id.details_star5)


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = this.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }

        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


}