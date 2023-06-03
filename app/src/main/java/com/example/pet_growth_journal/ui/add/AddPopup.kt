package com.example.pet_growth_journal.ui.add

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.CancellationSignal
import android.provider.MediaStore
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pet_growth_journal.R
import com.example.pet_growth_journal.databinding.CommonAddBottomPopupBinding
import com.example.pet_growth_journal.ui.common.BottomDialogPopupFragment
import com.example.pet_growth_journal.ui.common.PopupController
import com.example.pet_growth_journal.ui.common.PopupTransactionProvider
import com.example.pet_growth_journal.ui.common.ViewModelPopupController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.lang.IllegalStateException

class AddPopup(
    private val viewModelPopupController: ViewModelPopupController
) : BottomDialogPopupFragment() {

    override val TAG = "AddPopup"

    override fun getChildFragment(): Fragment {
        changePopupBackground(R.drawable.background_bottom_popup)
        animatePopup(R.anim.slide_bottom_top)
        return AddPopupFragment(viewModelPopupController)
    }

    fun startPopup(@IdRes id: Int, transactionProvider: PopupTransactionProvider) {
        if (!isAdded) {
            transactionProvider
                .beginTransaction()
                ?.add(id, this, TAG)
                ?.commit()
        }
    }
}

class AddPopupFragment(
    private val viewModelPopupController: ViewModelPopupController
): Fragment() {
    lateinit var binding: CommonAddBottomPopupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val addPopupController =
            viewModelPopupController.usePopupControllerList.find { it is AddPopupController }
        if (addPopupController !is AddPopupController) throw IllegalStateException(
            "ViewModelPopupController not has AddPopupController"
        )

        binding = CommonAddBottomPopupBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                controller = addPopupController
                popupController = viewModelPopupController
            }

        return binding.root
    }

//    private val addViewModel: AddViewModel by viewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = BottomsheetdialogAddPictureFragmentBinding.inflate(inflater, container, false).apply {
//            lifecycleOwner = viewLifecycleOwner
//            viewModel = addViewModel
//        }
//
//        addViewModel.pictureType.observe(this) { type ->
//            when (type) {
//                PictureType.GALLERY -> {
//                    pickFromGallery()
//                }
//                PictureType.CAMERA -> {
//                    pickFromCamera()
//                }
//            }
//        }
//
//        binding.btnDummyPicture.setOnClickListener {
//            addViewModel.setCurrentType(CurrentType.CATEGORY)
//        }
//        return binding.root
//    }
//
//    private fun pickFromGallery() {
//        val mimeType = arrayOf("image/jpeg", "image/png")
//        galleryLauncher.launch(Intent(Intent.ACTION_PICK).apply {
//            type = "image/*"
//            putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
//        })
//    }
//
//    private val galleryLauncher: ActivityResultLauncher<Intent> =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            val thumbnail = context?.contentResolver?.loadThumbnail((it.data?.data ?: "") as Uri, Size(100, 100), CancellationSignal())
//            binding.ivSelectPicture.setImageBitmap(thumbnail)
//            binding.btnDummyPicture.visibility = View.VISIBLE
//
//        }
//
//    private fun pickFromCamera() {
//        cameraLauncher.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
//    }
//
//    private val cameraLauncher: ActivityResultLauncher<Intent> =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            val bundle = it.data?.extras
//            val imageBitmap = bundle?.get("data") as Bitmap
//            binding.ivSelectPicture.setImageBitmap(imageBitmap)
//            binding.btnDummyPicture.visibility = View.VISIBLE
//        }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}

class AddPopupDelegate: AddPopupController {

}

interface AddPopupController: PopupController {

}
