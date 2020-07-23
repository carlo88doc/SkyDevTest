package it.carlo.skydevtest.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import it.carlo.skydevtest.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFragment(HomeFragment.newInstance(), HomeFragment.TAG)

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


    private fun showFragment(fragment:Fragment, tag:String){

        val isTheSameFragment = supportFragmentManager?.findFragmentByTag(tag)?.tag?.equals(tag, false)!=null

        if (isTheSameFragment){
            supportFragmentManager.popBackStackImmediate()
        }

        supportFragmentManager.beginTransaction().replace(R.id.container, fragment, tag).addToBackStack(tag).commitAllowingStateLoss()
    }


}
