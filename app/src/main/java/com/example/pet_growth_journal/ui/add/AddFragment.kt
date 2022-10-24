package com.example.pet_growth_journal.ui.add

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.CancellationSignal
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.pet_growth_journal.databinding.BottomsheetdialogAddFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddFragment : BottomSheetDialogFragment() {

    private var _binding: BottomsheetdialogAddFragmentBinding? = null
    private val binding: BottomsheetdialogAddFragmentBinding
        get() = _binding!!

    private val addViewModel: AddViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomsheetdialogAddFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = addViewModel
        }

        addViewModel.pictureType.observe(this, Observer { type ->
            when (type) {
                PictureType.GALLERY -> {
                    pickFromGallery()
                }
                PictureType.CAMERA -> {
                    pickFromCamera()
                }
            }
        })
        return binding.root
    }

    private fun pickFromGallery() {
        val mimeType = arrayOf("image/jpeg", "image/png")
        galleryLauncher.launch(Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
        })
    }

    private val galleryLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val thumbnail = context?.contentResolver?.loadThumbnail((it.data?.data ?: "") as Uri, Size(100, 100), CancellationSignal())
            binding.ivSelectPicture.setImageBitmap(thumbnail)

        }

    private fun pickFromCamera() {
        cameraLauncher.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))

    }

    private val cameraLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val bundle = it.data?.extras
            val imageBitmap = bundle?.get("data") as Bitmap
            binding.ivSelectPicture.setImageBitmap(imageBitmap)
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}