package cz.utb.fai.imgrestapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cz.utb.fai.imgrestapp.R
import cz.utb.fai.imgrestapp.databinding.ActivityApodviewBinding
import cz.utb.fai.imgrestapp.model.ApodInfoViewModel

class ApodViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApodviewBinding
    private lateinit var viewModel: ApodInfoViewModel

    private lateinit var webView: WebView
    private lateinit var titleTextView: TextView
    private lateinit var descriptionText: TextView
    private lateinit var saveButton: Button
    private lateinit var returnButton: Button


    fun initLayouts() {
        webView = findViewById(R.id.webView)
        titleTextView = findViewById(R.id.titleTextView)
        descriptionText = findViewById(R.id.explanationText)
        saveButton = findViewById(R.id.saveButton)
        returnButton = findViewById(R.id.returnButton)

    }
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apodview)

        binding = ActivityApodviewBinding.inflate(layoutInflater)

        val view  = binding.root

        setContentView(view)
        initLayouts()

        val viewModelId = intent.getStringExtra("date")




        setContentView(view)
        returnButton.setOnClickListener {
            val intent = Intent(this, ApodFormActivity::class.java)
            startActivity(intent)
            finish()
        }


        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    view?.loadUrl(url)
                }
                return true
            }
        }
        webView.loadUrl(viewModel.apodinfoValue.value?.hdurl.toString())
    }




}