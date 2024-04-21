package com.example.slide_image

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.slide_image.databinding.ActivityMainBinding
import com.example.slide_image.databinding.ItemSlideBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ImageSliderAdapter
    private val list = ArrayList<imageData>()
    private lateinit var dots: ArrayList<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list.add(
            imageData(
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.detik.com%2Fedu%2Fdetikpedia%2Fd-6672620%2Fteknik-menggambar-pemandangan-dengan-crayon-serta-contohnya&psig=AOvVaw1sZCjPmim0zW6_-Bh086aX&ust=1713772922341000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCID75ePr0oUDFQAAAAAdAAAAABAE"
            )
        )
        list.add(
            imageData(
                "https://drive.google.com/file/d/1BQwWUwQtU8oGP-SrMFsgm7_77pYvLMhk/view?usp=drive_link"
            )
        )

        adapter = ImageSliderAdapter(list)
        binding.viewPager.adapter = adapter
        dots = ArrayList()
        setIndicator()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })

    }

    private fun selectedDot(position: Int) {
        for(i in 0 until list.size)
            if (i == position)
                dots[i].setTextColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_primary))
        else
            dots[i].setTextColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_secondary))

    }

    private fun setIndicator() {
        for (i in 0 until list.size) {
            dots.add(TextView(this))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                dots[i].text = Html.fromHtml("&#9679")
            }
            dots[i].textSize = 18f
            binding.dotsIndicator.addView(dots[i])
        }
    }
}