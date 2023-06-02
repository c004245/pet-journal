package com.example.pet_growth_journal.ui.common

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pet_growth_journal.R
import com.example.pet_growth_journal.result.Event

interface CustomPopup {
    fun beforeSetupPopupChildrenFragments(parentView: View)

    //Override this method as final in abstract class.
    fun inflateChildrenFragments(parentView: View)

    //Override this method when you need to do something onViewCreated phase(ex)Add livedata listeners)
    fun onCreateChildrenFragments(parentView: View)

    fun closePopup()

    fun animatePopup(animResId: Int)

    fun changePopupBackground(resId: Int)

    val TAG: String


}

abstract class DialogPopupFragment(private val dialogType: DialogType) : Fragment(), CustomPopup {
    var parentView: View? = null

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewId = when (dialogType) {
            DialogType.BOTTOM_DIALOG -> R.layout.common_bottom_popup

        }

        val view = inflater.inflate(viewId, container, false)

        parentView = view
        if (dialogType == DialogType.BOTTOM_DIALOG) {
            parentView?.findViewById<View>(R.id.v_background)?.setOnClickListener {
                closePopup()
            }
        }

        beforeSetupPopupChildrenFragments(view)
        onCreateChildrenFragments(view)
        inflateChildrenFragments(view)
        setupOnPressBackButton()
        return view
    }

    protected fun commitFragment(@IdRes id: Int, fragment: Fragment, tag: String) {
        val fragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.add(
            id,
            fragment, tag
        ).commit()
    }

    override fun closePopup() {
        onClosePopup()
        parentFragmentManager.findFragmentByTag(TAG)?.also {
            parentFragmentManager.beginTransaction().remove(it).commit()
        } ?: Log.d(TAG, "Cannot find fragment with $TAG")
    }

    open fun setupOnPressBackButton() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                closePopup()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    open fun onClosePopup() {

    }

    override fun animatePopup(animResId: Int) {
        AnimationUtils.loadAnimation(requireContext(), animResId).apply {
            parentView?.findViewById<ConstraintLayout>(R.id.cl_dialog_parents)?.startAnimation(this)
        }
    }

    override fun changePopupBackground(resId: Int) {
        parentView?.findViewById<ConstraintLayout>(R.id.cl_dialog)?.apply {
            setBackgroundResource(resId)
        }
    }
}


abstract class BottomDialogPopupFragment : DialogPopupFragment(DialogType.BOTTOM_DIALOG) {

    abstract fun getChildFragment(): Fragment

    private var _fragment: Fragment? = null
    val fragment: Fragment?
        get() {
            if (_fragment == null) Log.e(TAG, "Popup's small fragment is not inflated.")
            return _fragment
        }

    final override fun inflateChildrenFragments(parentView: View) {
        val fragment = getChildFragment()
        commitFragment(
            R.id.cl_dialog,
            fragment, TAG
        )
        _fragment = childFragmentManager.findFragmentByTag(TAG)
    }

    override fun onCreateChildrenFragments(parentView: View) {}

    final override fun beforeSetupPopupChildrenFragments(parentView: View) {}
}

abstract class ViewModelPopupController : ViewModel() {
    abstract val usePopupControllerList: List<PopupController>

    private val _showPopup: MutableLiveData<Event<PopupName>> = MutableLiveData()
    val showPopup: LiveData<Event<PopupName>>
        get() = _showPopup

    private val _closePopup: MutableLiveData<Event<PopupName>> = MutableLiveData()
    val closePopup: LiveData<Event<PopupName>> = _closePopup

    open fun showPopup(name: PopupName) {
        _showPopup.postValue(Event(name))
    }

    open fun onClosePopup(name: PopupName) {
        _closePopup.postValue(Event(name))
    }
}


interface PopupController {

}

enum class PopupName {
   ADD
}

enum class DialogType {
    BOTTOM_DIALOG
}


class DialogPopupFragmentManager(private val fragmentManager: FragmentManager?) :
    PopupTransactionProvider {
    override fun beginTransaction(): FragmentTransaction? {
        return fragmentManager?.beginTransaction()
    }
}

interface PopupTransactionProvider {
    fun beginTransaction(): FragmentTransaction?
}