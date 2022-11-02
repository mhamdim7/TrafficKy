package com.mhamdi.trafficky.extensions

import android.graphics.drawable.TransitionDrawable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

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

object FragmentExtensions {
    fun <T> Fragment.viewLifecycle(): ReadWriteProperty<Fragment, T> =
        object : ReadWriteProperty<Fragment, T>, DefaultLifecycleObserver {
            private var binding: T? = null
            private val ERROR = "Called before onCreateView or after onDestroyView."

            init {
                // Observe the view lifecycle of the Fragment.
                // The view lifecycle owner is null before onCreateView and after onDestroyView.
                // The observer is automatically removed after the onDestroy event.
                viewLifecycleOwnerLiveData.observe(this@viewLifecycle) { owner: LifecycleOwner? ->
                    owner?.lifecycle?.addObserver(this)
                }
            }

            override fun onDestroy(owner: LifecycleOwner) {
                binding = null
            }

            override fun getValue(
                thisRef: Fragment,
                property: KProperty<*>
            ): T = this.binding ?: error(ERROR)

            override fun setValue(
                thisRef: Fragment,
                property: KProperty<*>,
                value: T
            ) {
                this.binding = value
            }
        }

}