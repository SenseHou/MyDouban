package com.example.mydouban.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mydouban.model.MovieSubject
import com.example.mydouban.repository.MovieTopRepository

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    val movieSubjectsTop6 = MutableLiveData<List<MovieSubject>>()
    private val repository = MovieTopRepository()

    fun getMovieTop6() {
        repository.getMovieTop6 { subjects ->
            movieSubjectsTop6.postValue(subjects)
        }
    }
}
