package com.example.interviewtask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.interviewtask.ui.fragment.FeedFragment
import com.example.interviewtask.ui.fragment.VideoFragment
import com.example.roundedtablayout.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {
    /*private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_hos_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.videoBtn.setOnClickListener {
            navController.navigate(R.id.videoFragment)
        }
        binding.feedBtn.setOnClickListener {
            navController.navigate(R.id.feedFragment)
        }
    }*/
    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.pager)
        tabLayout = findViewById(R.id.
        tab_layout)
        setAdapters()

    }

    private fun setAdapters() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(VideoFragment(), "Videos")
        adapter.addFragment(FeedFragment(), "Feeds")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
