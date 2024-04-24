package com.nalldev.naltoast.util

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation

fun View.fadeIn(duration: Long = 500, completion: (() -> Unit)? = null) {
    val fadeIn = AlphaAnimation(0f, 1f).apply {
        this.duration = duration
        fillAfter = true
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                completion?.invoke()
            }
        })
    }
    this.startAnimation(fadeIn)
}

fun View.fadeOut(duration: Long = 500, completion: (() -> Unit)? = null) {
    val fadeOut = AlphaAnimation(1f, 0f).apply {
        this.duration = duration
        fillAfter = true
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                completion?.invoke()
            }
        })
    }
    this.startAnimation(fadeOut)
}
