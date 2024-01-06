package cz.utb.fai.imgrestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import cz.utb.fai.imgrestapp.databinding.ActivityApodinfoBinding

class ApodInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityApodinfoBinding

    private lateinit var viewModel: ApodInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apodinfo)

        binding = ActivityApodinfoBinding.inflate(layoutInflater)
        val view  = binding.root

        setContentView(view)

        binding.txtLbl.text = "TEST"

        val app = application as MyApplication
        // singleton
        viewModel = ViewModelProvider(this, ApodInfoViewModelFactory(app.repository)).get(ApodInfoViewModel::class.java)





    }
}