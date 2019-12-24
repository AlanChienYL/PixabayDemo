package com.pixabay.views.epoxy

import androidx.lifecycle.Lifecycle.Event.ON_PAUSE
import androidx.lifecycle.Lifecycle.Event.ON_RESUME
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.airbnb.epoxy.EpoxyVisibilityTracker
import timber.log.Timber

/**
 * Created by AlanChien on 23,December,2019.
 */
class EpoxyTrackerService(private val brv: BaseRecyclerView) : LifecycleObserver {

    @OnLifecycleEvent(ON_RESUME)
    fun attachVisibleTracker() {
        Timber.d("in attachVisibleTracker")
        EpoxyVisibilityTracker().attach(brv)
    }

    @OnLifecycleEvent(ON_PAUSE)
    fun detachVisibleTracker() {
        Timber.d("in detachVisibleTracker")
        EpoxyVisibilityTracker().detach(brv)
    }
}