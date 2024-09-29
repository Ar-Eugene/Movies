package com.example.movies.ui.poster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.movies.util.Creator
import com.example.movies.R
import com.example.movies.presentation.poster.PosterPresenter
import com.example.movies.presentation.poster.PosterView

class PosterActivity : AppCompatActivity(), PosterView {

    private lateinit var poster: ImageView
    private lateinit var posterPresenter: PosterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imageUrl = intent.extras?.getString("poster", "") ?: ""
        posterPresenter = Creator.providePosterPresenter(this, imageUrl)
        setContentView(R.layout.activity_poster)
        poster = findViewById(R.id.poster)
        posterPresenter.onCreate()
    }

    override fun setupPosterImage(url: String) {
        Glide.with(applicationContext)
            .load(url)
            .into(poster)
    }
}