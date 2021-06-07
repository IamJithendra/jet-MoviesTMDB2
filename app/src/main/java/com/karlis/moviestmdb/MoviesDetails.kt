package com.karlis.moviestmdb

import java.io.Serializable

data class MoviesDetails(
    val id: String,
    val original_language: String,
    val popularity: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: String,
): Serializable