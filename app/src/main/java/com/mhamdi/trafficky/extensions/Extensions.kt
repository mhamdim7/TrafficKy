package com.mhamdi.trafficky.extensions

import android.graphics.drawable.TransitionDrawable
import android.view.View

object ViewExtensions {

    private const val BULB_ANIMATION_DURATION = 300

    fun View.updateTransitionState(on: Boolean) = runCatching {
        (background as TransitionDrawable).run {
            if (on) {
                reverseTransition(0)
                startTransition(BULB_ANIMATION_DURATION)
            } else {
                startTransition(0)
                reverseTransition(BULB_ANIMATION_DURATION)
            }
        }
    }

}