package com.nalldev.naltoast.view

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LifecycleCoroutineScope
import com.nalldev.naltoast.R
import com.nalldev.naltoast.util.Duration
import com.nalldev.naltoast.util.Type
import com.nalldev.naltoast.util.fadeIn
import com.nalldev.naltoast.util.fadeOut
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class NalToast(private val rootView: View, private val type: Type = Type.SUCCESS) {
    private lateinit var toastView: View
    private var popupWindow: PopupWindow? = null
    private val executor = Executors.newSingleThreadScheduledExecutor()

    private fun initializeView(type: Type) {
        val inflater = LayoutInflater.from(rootView.context)
        toastView = inflater.inflate(R.layout.custom_toast, null)
        setType(type)
    }

    fun show(message: String, duration: Duration = Duration.SHORT, lifecycleScope: LifecycleCoroutineScope? = null) {
        initializeView(type)
        val textView: TextView = toastView.findViewById(R.id.tv_subtitle)
        textView.text = message

        popupWindow?.dismiss()
        popupWindow = PopupWindow(
            toastView,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            showAtLocation(rootView, Gravity.BOTTOM or Gravity.CENTER, 0, 0)
        }

        val displayTime = if (duration == Duration.SHORT) 2250L else 5000L

        if (lifecycleScope == null) {
            toastView.fadeIn(500)
            executor.schedule({
                toastView.fadeOut(500) {
                    popupWindow?.dismiss()
                }
            }, displayTime + 500, TimeUnit.MILLISECONDS)
        } else {
            lifecycleScope.launch {
                toastView.fadeIn(500)
                delay(displayTime)
                toastView.fadeOut(500) {
                    popupWindow?.dismiss()
                }
            }
        }
    }

    private fun setType(type: Type) {
        val view = toastView.findViewById<View>(R.id.v_bubble)
        val imageView = toastView.findViewById<ImageView>(R.id.iv_bottom_toast)
        val cardView = toastView.findViewById<CardView>(R.id.cv_toast)
        val title = toastView.findViewById<TextView>(R.id.tv_title)

        when (type) {
            Type.SUCCESS -> {
                view.setBackgroundResource(R.drawable.toast_buble_success)
                imageView.setImageResource(R.drawable.toast_bottom_success)
                cardView.setCardBackgroundColor(Color.parseColor("#03A65A"))
                title.text = "Well done!"
            }
            Type.FAIL -> {
                view.setBackgroundResource(R.drawable.toast_buble_fail)
                imageView.setImageResource(R.drawable.toast_bottom_fail)
                cardView.setCardBackgroundColor(Color.parseColor("#F63E50"))
                title.text = "Oh snap!"
            }
            Type.INFO -> {
                view.setBackgroundResource(R.drawable.toast_buble_info)
                imageView.setImageResource(R.drawable.toast_bottom_info)
                cardView.setCardBackgroundColor(Color.parseColor("#739BE5"))
                title.text = "Hi there!"
            }
        }
    }
}
