package com.example.mydouban.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydouban.R
import com.example.mydouban.databinding.DetailHeaderBinding
import com.example.mydouban.databinding.DetailOnlinePlaysBinding
import com.example.mydouban.databinding.DetailRatingBinding
import com.example.mydouban.model.MovieDetail
import com.example.mydouban.model.RatingDetail
import com.example.mydouban.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.detail_header.*
import kotlinx.android.synthetic.main.detail_online_plays.*
import kotlinx.android.synthetic.main.detail_rating.*

class DetailActivity : AppCompatActivity() {

    private val detailViewModel by lazy { DetailViewModel(this.application) }
    private var movieTitle: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getMovieDetail()
        onScrollChangeListener()
    }

    private fun getMovieDetail() {
        detailViewModel.detailLiveData.observe(this, Observer { detailDto ->
            val headerBinding = DataBindingUtil.bind<DetailHeaderBinding>(detailHeaderView)
            headerBinding?.detail = MovieDetail(detailDto)
            val ratingBinding =
                DataBindingUtil.bind<DetailRatingBinding>(movieDetailRatingView)

            ratingBinding?.ratingDetail = RatingDetail(
                detailDto.rating,
                detailDto.wishCount,
                detailDto.collectCount,
                detailDto.ratingsCount
            )

            if (detailDto.videos.isNotEmpty()) {
                val onlinePlaysBinding =
                    DataBindingUtil.bind<DetailOnlinePlaysBinding>(detailOnlinePlays)
                onlinePlaysBinding?.videos = detailDto.videos
            } else {
                detailOnlinePlays.visibility = View.GONE
            }

            val tagsManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            tagListView.layoutManager = tagsManager
            tagListView.adapter = TagsAdapter(detailDto.tags)


            movieTitle = detailDto.title
            summary.text = detailDto.summary
        })
        detailViewModel.getMovieDetail("1292226")
    }

    private fun onScrollChangeListener() {
        detailScrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            val titleBottomPosition = detailTitleView.y + detailTitleView.height
            appBarTitle.text = if (scrollY >= titleBottomPosition) movieTitle else resources.getString(R.string.detail_app_bar)
        }
    }
}
