package com.nalldev.naltoast.view

import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import com.nalldev.naltoast.R
import com.nalldev.naltoast.extensions.fadeIn
import com.nalldev.naltoast.extensions.fadeOut
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object NalToast {

    fun show(view: View, message: String, lifecycleScope: LifecycleCoroutineScope? = null) {
        val inflater = LayoutInflater.from(view.context)
        val toastView = inflater.inflate(R.layout.custom_toast, null)
        val textView : TextView = toastView.findViewById(R.id.tv_subtitle)
        textView.text = message

        val popupWindow = PopupWindow(
            toastView,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            showAtLocation(view, Gravity.BOTTOM or Gravity.CENTER, 0, 0)
        }

        if (lifecycleScope == null) {
            val handler = Handler(Looper.getMainLooper())
            handler.post {
                toastView.fadeIn(500, completion = {
                    handler.postDelayed({
                        toastView.fadeOut(500) {
                            popupWindow.dismiss()
                        }
                    }, 2250)
                })
            }
        } else {
            lifecycleScope.launch {
                toastView.fadeIn(500)
                delay(2250)
                toastView.fadeOut(500) {
                    popupWindow.dismiss()
                }
            }
        }
    }
}