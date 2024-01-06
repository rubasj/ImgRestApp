package cz.utb.fai.imgrestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import cz.utb.fai.imgrestapp.databinding.ActivityApodinfoBinding
import cz.utb.fai.imgrestapp.databinding.ActivityRestinfoBinding

class ApodInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityApodinfoBinding

    private lateinit var viewModel: ApodInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apodinfo)

        binding = ActivityApodinfoBinding.inflate(layoutInflater)
        val view  = binding.root

        setContentView(view)

        binding.txtLbl.text = "I hate UTB"

        val app = application as MyApplication
        viewModel = ViewModelProvider(this, ApodInfoViewModelFactory(app.repository).get


    }
}