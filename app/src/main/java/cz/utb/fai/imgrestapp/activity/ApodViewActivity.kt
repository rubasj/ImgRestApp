package cz.utb.fai.imgrestapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cz.utb.fai.imgrestapp.MyApplication
import cz.utb.fai.imgrestapp.R
import cz.utb.fai.imgrestapp.api.ApodRequestDto
import cz.utb.fai.imgrestapp.databinding.ActivityApodinfoBinding
import cz.utb.fai.imgrestapp.model.ApodInfoViewModel
import cz.utb.fai.imgrestapp.model.ApodInfoViewModelFactory

class ApodViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApodinfoBinding
    private lateinit var viewModel: ApodInfoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apodview)


        binding = ActivityApodinfoBinding.inflate(layoutInflater)
        val view  = binding.root

        setContentView(view)


        // Get the submitted date from the Intent
        val submittedDate = intent.getStringExtra("DATE_")

        val app = application as MyApplication
        // singleton
        viewModel = ViewModelProvider(this, ApodInfoViewModelFactory(app.repository)).get(
            ApodInfoViewModel::class.java)


        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val request = submittedDate?.let { ApodRequestDto(date = it) }
        if (request != null) {
            viewModel.getApodInfo(request)
        }
        else {
            System.err.println("Request does not exist!")
        }

    }
}