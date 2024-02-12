package com.example.newsapp

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.util.isInternetAvailable
import com.example.newsapp.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val adapter = HomeAdapter()
    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            newsArticleRv.layoutManager = LinearLayoutManager(this@HomeActivity)
            newsArticleRv.adapter = adapter
        }
        loadNews()
    }

    override fun onRestart() {
        super.onRestart()
        loadNews()
    }

    private fun loadNews(){
        if (!isInternetAvailable(this@HomeActivity)) {
            binding.offlineImage.visibility = View.VISIBLE
            binding.offlineText.visibility = View.VISIBLE
            toast("you are offline")
        } else {
            viewModel.getNews()
            viewModel.newsList.observe(this){
                adapter.submitList(it.data!!.articles)
            }
            binding.offlineImage.visibility = View.GONE
            binding.offlineText.visibility = View.GONE
        }
    }

}