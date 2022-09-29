package  com.example.pet_growth_journal.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.NavController

class DestinationChangeObserver(
    val callback: NavController.OnDestinationChangedListener,
    private val navController: NavController
) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun connectListener() {
        navController
            .addOnDestinationChangedListener(callback)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun disconnectListener() {
        navController.removeOnDestinationChangedListener(callback)
    }

}