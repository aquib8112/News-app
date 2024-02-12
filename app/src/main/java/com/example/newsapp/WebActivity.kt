package com.example.newsapp

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class WebActivity : AppCompatActivity() {
    private lateinit var progressBar : ProgressBar
    private lateinit var webPage : WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val backButton = findViewById<ImageButton>(R.id.back_button)
        webPage = findViewById(R.id.wep_page)
        progressBar =findViewById(R.id.progressBar)

        backButton.setOnClickListener {
            goToHomePage()
        }

        val url = intent.getStringExtra("url") ?: return // Handle missing URL gracefully
        webPage.settings.javaScriptEnabled
        webPage.webViewClient = MyWebViewClient()
        webPage.loadUrl(url)

    }
    inner class MyWebViewClient : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progressBar.visibility = View.VISIBLE // Show the progress bar
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.GONE // Hide the progress bar
        }
    }

    private fun goToHomePage() {
        startActivity(Intent(this@WebActivity, HomeActivity::class.java))
        finish()
    }
}