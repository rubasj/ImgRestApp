package cz.utb.fai.imgrestapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import cz.utb.fai.imgrestapp.MyApplication
import cz.utb.fai.imgrestapp.R
import cz.utb.fai.imgrestapp.api.ApodRequestDto
import cz.utb.fai.imgrestapp.databinding.ActivityApodviewBinding
import cz.utb.fai.imgrestapp.model.ApodInfoViewModel
import cz.utb.fai.imgrestapp.model.ApodInfoViewModelFactory

class ApodViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApodviewBinding
    private lateinit var viewModel: ApodInfoViewModel

    private lateinit var imageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var descriptionText: TextView
    private lateinit var saveButton: Button
    private lateinit var returnButton: Button


    fun initLayouts() {
        imageView = findViewById(R.id.imageView)
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

        val stringDate = intent.getStringExtra("date").toString()

        val app = application as MyApplication
        viewModel = ViewModelProvider(this, ApodInfoViewModelFactory(app.repository))
            .get(ApodInfoViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val request = ApodRequestDto(date = stringDate)

        viewModel.getApodInfo(request)
        val urlData = viewModel.apodinfoValue.value?.url.toString()
        DownloadImageFromInternet(findViewById(R.id.imageView)).execute(urlData)

        setContentView(view)

        returnButton.setOnClickListener {
            val intent = Intent(this, ApodFormActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    @SuppressLint("StaticFieldLeak")
    @Suppress("DEPRECATION")
    private inner class DownloadImageFromInternet(var imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
        init {
            Toast.makeText(applicationContext, "Please wait, it may take a few minute...", Toast.LENGTH_SHORT).show()
        }
        override fun doInBackground(vararg urls: String): Bitmap? {
            val imageURL = urls[0]
            var image: Bitmap? = null
            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)
            }
            catch (e: Exception) {
                Log.e("Error Message", e.message.toString())
                e.printStackTrace()
            }
            return image
        }
        override fun onPostExecute(result: Bitmap?) {
            imageView.setImageBitmap(result)
        }
    }




}