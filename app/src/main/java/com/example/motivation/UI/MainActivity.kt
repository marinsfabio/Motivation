package com.example.motivation.UI

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.view.View
import androidx.core.content.ContextCompat
import com.example.motivation.DATA.Mock
import com.example.motivation.INFRA.MotivationConstantes
import com.example.motivation.R
import com.example.motivation.INFRA.SecurityPreferences
import com.example.motivation.databinding.ActivityMainBinding


class MainActivity : ComponentActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var category = MotivationConstantes.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleUserName()
        handleFilter(R.id.image_all)
        handleNextPhrase()

        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_new_phrase){
            handleNextPhrase()
        } else if(view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)) {
            handleFilter(view.id)
        }
    }

    private fun handleNextPhrase(){
        binding.textPhrase.text = Mock().getPhrase(category)
    }

    private fun handleFilter(id: Int) {
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.black))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.black))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.black))

        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                category = MotivationConstantes.FILTER.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                category = MotivationConstantes.FILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                category = MotivationConstantes.FILTER.SUNNY
            }
        }
    }

    fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstantes.KEY.USER_NAME)
        binding.textUserName.text = "Olá, $name!"
    }
}

