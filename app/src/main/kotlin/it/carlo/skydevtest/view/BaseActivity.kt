package it.carlo.skydevtest.view

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import it.carlo.skydevtest.R


open class BaseActivity : AppCompatActivity() {

    protected fun showFragment(fragment: Fragment, tag:String){

        val isTheSameFragment = supportFragmentManager.findFragmentByTag(tag)?.tag?.equals(tag, false) ?: false

        if (isTheSameFragment){
            supportFragmentManager.popBackStackImmediate()
        }

        supportFragmentManager.beginTransaction().replace(R.id.container, fragment, tag).addToBackStack(tag).commitAllowingStateLoss()
    }

}