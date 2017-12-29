package com.flyinbed.iapopup

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout



/**
 * 作者：flyinbed on 2017/11/5 16:46
 * 邮箱：zhanghuaiha@gmail.com
 */


/**
 * 隐藏显示动画
 */
fun Context.setTrans(view: View, show: Boolean, value: Float) {
    var va: ValueAnimator? = null
    va = when (show) {
    //显示view
        true -> {
            ValueAnimator.ofInt(0, this.dp2px(value))
        }
    //隐藏view
        false -> {
            ValueAnimator.ofInt(this.dp2px(value), 0)
        }
    }
    va?.addUpdateListener { valueAnimator ->
        val h = valueAnimator.animatedValue
        view.layoutParams.height = h as Int
        view.requestLayout()

    }
    va?.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            super.onAnimationEnd(animation)
            when (show) {
                true -> {
//                    view.visibility = View.VISIBLE
                }
                false -> {
                    view.visibility = View.GONE
                }
            }
        }
    })
    va.duration = 500
    va.start()
}


fun Context.dp2px(dpValue: Float): Int {
    val scale = this.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

