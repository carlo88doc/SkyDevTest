package it.carlo.skydevtest.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import it.carlo.skydevtest.R
import it.carlo.skydevtest.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private lateinit var mainViewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState==null){
            showFragment(HomeFragment.newInstance(), HomeFragment.TAG)
        }

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.progressLiveData.observe(this, Observer {showEvent ->

            showEvent.getContentIfNotHandled()?.let {show ->
                progressBarLayout.visibility = if (show) View.VISIBLE else View.GONE
            }

        })

        addBottomMenuListener()
    }

    private fun addBottomMenuListener(){
        bottomNavigationView.setOnNavigationItemSelectedListener {menuItem ->

            var tag = ""

            val fragment = when (menuItem.itemId){
                R.id.navigation_home -> {
                    tag = HomeFragment.TAG
                    HomeFragment.newInstance()
                }
                R.id.navigation_photos -> {
                    tag = PhotosFragment.TAG
                    PhotosFragment.newInstance()
                }
                R.id.navigation_settings -> {
                    tag = SettingsFragment.TAG
                    SettingsFragment.newInstance()
                }
                else -> null
            }

            if (fragment!=null){
                showFragment(fragment, tag)
                true
            }else{
                false
            }


        }
    }

}
