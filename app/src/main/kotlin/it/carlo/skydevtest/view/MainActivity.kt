package it.carlo.skydevtest.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import it.carlo.skydevtest.R
import it.carlo.skydevtest.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    lateinit var mainViewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inflateBottomMenu()
        showFragment(HomeFragment.newInstance(), HomeFragment.TAG)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun inflateBottomMenu(){
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

    private fun showProgressBar(){
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        progressBar.visibility = View.GONE
    }

}
