package com.example.interviewtask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.interviewtask.ui.fragment.FeedFragment
import com.example.interviewtask.ui.fragment.VideoFragment
import com.example.roundedtablayout.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

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
