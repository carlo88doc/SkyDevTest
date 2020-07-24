package it.carlo.skydevtest.utils

import android.os.Build
import android.text.Html
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import it.carlo.skydevtest.BuildConfig
import java.text.SimpleDateFormat
import java.util.*

private const val TAG_LOG = "SkyDevTestLogging"

const val FORMAT_DATE_FULL = "dd/MM/yyyy HH:mm"

@Suppress( "deprecation" ) //managed the deprecation behalf current SDK
fun TextView.setHtmlText(text:String?){
    this.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(text ?: "", Html.FROM_HTML_MODE_LEGACY)
    }else{
        Html.fromHtml(text ?: "")
    }
}

fun EditText.getTypedText():String{
    return this.text.toString()
}


fun Throwable.logError(){
    if (BuildConfig.DEBUG){
        Log.e(TAG_LOG, this.message, this)
    }
}

fun log(message:String?){
    if (BuildConfig.DEBUG && message?.isNotEmpty()==true){
        Log.d(TAG_LOG, message)
    }
}

fun Long?.toDateString(dateFormat: String): String {
    return if (this==null){
        ""
    }else{
        SimpleDateFormat(dateFormat).format(Date(this))
    }
}

