package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class webview : AppCompatActivity() {
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        webView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()

        // this will load the url of the website
        webView.loadUrl("https://canvas.rku.ac.in/login/canvas")
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)
    }

    override fun onBackPressed() {
        if (webView.canGoBack())
            webView.goBack()
        else
            super.onBackPressed()
    }
    }
