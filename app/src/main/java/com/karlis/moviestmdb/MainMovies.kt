package com.karlis.moviestmdb

import org.json.JSONArray

data class MainMovies(
    val page: Int? = null,
    val results: JSONArray,
    val total_pages: Int? = null,
    val total_results: Int? = null
)