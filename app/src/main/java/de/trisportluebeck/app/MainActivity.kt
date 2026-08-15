package de.trisportluebeck.app

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    private val startUrl = "https://intern.trisport-luebeck.de/"

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView = WebView(this)
        setContentView(webView)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.databaseEnabled = true
        webView.settings.loadsImagesAutomatically = true
        webView.settings.setSupportZoom(false)

        CookieManager.getInstance().setAcceptCookie(true)
        CookieManager.getInstance()
            .setAcceptThirdPartyCookies(webView, true)

        webView.webChromeClient = WebChromeClient()

        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {

                val host = request.url.host ?: return false

                val internal =
                    host == "intern.trisport-luebeck.de" ||
                    host.endsWith(".trisport-luebeck.de")

                if (!internal) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(request.url.toString())
                        )
                    )
                    return true
                }

                return false
            }
        }

        if (savedInstanceState == null) {
            webView.loadUrl(startUrl)
        } else {
            webView.restoreState(savedInstanceState)
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        webView.saveState(outState)
        super.onSaveInstanceState(outState)
    }
}
