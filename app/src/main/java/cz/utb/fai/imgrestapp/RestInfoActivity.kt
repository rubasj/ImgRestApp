package cz.utb.fai.imgrestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cz.utb.fai.imgrestapp.databinding.ActivityRestinfoBinding

class RestInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestinfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restinfo)

        binding = ActivityRestinfoBinding.inflate(layoutInflater)
        val view  = binding.root

        setContentView(view)

        binding.txtLbl.text = "I hate UTB"



    }
}