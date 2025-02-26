package dev.androidbroadcast.browserapp

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var urlInput: EditText
    private lateinit var searchButton: Button
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Browser App"

        webView = findViewById(R.id.webView)
        urlInput = findViewById(R.id.urlInput)
        searchButton = findViewById(R.id.searchButton)

        // Настройки WebView
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            cacheMode = WebSettings.LOAD_DEFAULT
        }

        searchButton.setBackgroundColor(Color.BLUE)
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                supportActionBar?.title = title ?: "Browser App"
            }
        }

        searchButton.setOnClickListener {
            val url = urlInput.text.toString()
            if (url.isNotEmpty()) {
                webView.loadUrl(url)
            }
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menu?.let {
            val isPortrait = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
            it.findItem(R.id.color_blue)?.isVisible = isPortrait
            it.findItem(R.id.color_green)?.isVisible = isPortrait
            it.findItem(R.id.color_violet)?.isVisible = isPortrait
            it.findItem(R.id.color_brown)?.isVisible = !isPortrait
            it.findItem(R.id.color_yellow)?.isVisible = !isPortrait
            it.findItem(R.id.color_orange)?.isVisible = !isPortrait
        }
        return true
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        invalidateOptionsMenu() // Пересоздает меню
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.color_blue -> searchButton.setBackgroundColor(Color.BLUE)
            R.id.color_green -> searchButton.setBackgroundColor(Color.GREEN)
            R.id.color_violet -> searchButton.setBackgroundColor(Color.MAGENTA)
            R.id.color_brown -> searchButton.setBackgroundColor(Color.rgb(165, 42, 42))
            R.id.color_yellow -> searchButton.setBackgroundColor(Color.YELLOW)
            R.id.color_orange -> searchButton.setBackgroundColor(Color.rgb(255, 165, 0))
        }
        return true
    }

    private fun setButtonColor(color: Int) {
        searchButton.setBackgroundColor(color)
    }

}
