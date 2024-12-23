package com.example.sunset

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import com.example.sunset.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val blueSky by lazy {
        ContextCompat.getColor(this, R.color.blue_sky)
    }
    private val sunSetSkyColor by lazy {
        ContextCompat.getColor(this, R.color.sunset_sky)
    }
    private val nightSkyColor by lazy {
        ContextCompat.getColor(this, R.color.night_sky)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.scene.setOnClickListener {
            StartAnimation()
        }
    }


    private fun StartAnimation() {
        val sunYStart = binding.sun.top.toFloat()
        val sunYEnd = binding.sky.height.toFloat()

        val heightAnimator = ObjectAnimator.ofFloat(binding.sun, "y", sunYStart, sunYEnd)
            .setDuration(10000)

        heightAnimator.interpolator = AccelerateInterpolator()

        val sunSetSkyAnimator =
            ObjectAnimator.ofInt(binding.sky, "backgroundColor", blueSky, sunSetSkyColor)
                .setDuration(10000)
        sunSetSkyAnimator.setEvaluator(ArgbEvaluator())
        val nightSkyAnimator =
            ObjectAnimator.ofInt(binding.sky, "backgroundColor", sunSetSkyColor, nightSkyColor)
                .setDuration(10000)
              nightSkyAnimator.setEvaluator(ArgbEvaluator())

        val animatorSet = AnimatorSet()
           animatorSet.play(heightAnimator)
            .with(sunSetSkyAnimator)
            .before(nightSkyAnimator)

animatorSet.start()

    }

}