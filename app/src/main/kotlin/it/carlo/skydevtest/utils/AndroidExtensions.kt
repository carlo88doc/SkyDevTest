package it.carlo.skydevtest.utils

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import it.carlo.skydevtest.BuildConfig

private const val TAG_LOG = "SkyDevTestLogging"

inline fun <T : EditText> T.afterTextChanged(crossinline f: T.(s: Editable?) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            f(s)
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //do nothing
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //do nothing
        }

    })
}

inline fun EditText.getTypedText():String{
    return this.text.toString()
}


fun Throwable.logError(){
    if (BuildConfig.DEBUG){
        Log.e(TAG_LOG, this?.message, this)
    }
}

fun log(message:String?){
    if (BuildConfig.DEBUG && message?.isNotEmpty()==true){
        Log.d(TAG_LOG, message)
    }
}

