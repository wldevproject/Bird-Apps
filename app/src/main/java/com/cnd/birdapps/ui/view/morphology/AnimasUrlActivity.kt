package com.cnd.birdapps.ui.view.morphology

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cnd.birdapps.databinding.ActivityAnimasUrlBinding


@SuppressLint("SetJavaScriptEnabled")
class AnimasUrlActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimasUrlBinding
    private lateinit var mainActivity: Activity
    private var mCustomView: View? = null
    private var mCustomViewCallback: WebChromeClient.CustomViewCallback? = null
    private var mFullscreenContainer: FrameLayout? = null


    companion object {
        const val EXTRA_DATA_3D_URL = "3D_URL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimasUrlBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainActivity = this

        val url = intent.getStringExtra(EXTRA_DATA_3D_URL)
        onShowWebClient(url)
    }

    private fun onShowWebClient(url: String?) {
        binding.apply {
            webView.settings.javaScriptEnabled = true
            webView.settings.domStorageEnabled = true
            webView.settings.allowFileAccess = true

            webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    view.loadUrl("javascript:alert('Data Sedang Disiapkan')")
                }
            }

            webView.webChromeClient = object : WebChromeClient() {
                override fun onJsAlert(
                    view: WebView?,
                    url: String?,
                    message: String?,
                    result: JsResult?
                ): Boolean {
                    Toast.makeText(this@AnimasUrlActivity, message, Toast.LENGTH_LONG).show()
                    result?.confirm()
                    return true
//                    return super.onJsAlert(view, url, message, result)
                }

                private var mOriginalOrientation = 0
                private var mOriginalSystemUiVisibility = 0

                override fun getDefaultVideoPoster(): Bitmap? {
                    return BitmapFactory.decodeResource(
                        mainActivity.applicationContext.resources, 2130837573
                    )
                }

                @Suppress("DEPRECATION")
                override fun onHideCustomView() {
                    (mainActivity.window.decorView as FrameLayout).removeView(mCustomView)
                    mCustomView = null
                    mainActivity.window.decorView.systemUiVisibility =
                        mOriginalSystemUiVisibility
                    mainActivity.requestedOrientation = mOriginalOrientation
                    mCustomViewCallback!!.onCustomViewHidden()
                    mCustomViewCallback = null
                }


                @Suppress("DEPRECATION")
                override fun onShowCustomView(
                    paramView: View?,
                    paramCustomViewCallback: CustomViewCallback?
                ) {
                    if (mCustomView != null) {
                        onHideCustomView()
                        return
                    }
                    mCustomView = paramView
                    mOriginalSystemUiVisibility = mainActivity.window.decorView.systemUiVisibility
                    mOriginalOrientation = mainActivity.requestedOrientation
                    mCustomViewCallback = paramCustomViewCallback
                    (mainActivity.window.decorView as FrameLayout).addView(
                        mCustomView,
                        FrameLayout.LayoutParams(-1, -1)
                    )
                    mainActivity.window.decorView.systemUiVisibility = 3846
                }
            }

            webView.loadUrl(url.toString())
        }
    }
}