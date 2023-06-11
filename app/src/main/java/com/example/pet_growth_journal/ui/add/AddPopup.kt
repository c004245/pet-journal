package com.example.pet_growth_journal.ui.add

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
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pet_growth_journal.MainViewModel
import com.example.pet_growth_journal.R
import com.example.pet_growth_journal.databinding.CommonAddBottomPopupBinding
import com.example.pet_growth_journal.ui.common.BottomDialogPopupFragment
import com.example.pet_growth_journal.ui.common.PopupController
import com.example.pet_growth_journal.ui.common.PopupTransactionProvider
import com.example.pet_growth_journal.ui.common.ViewModelPopupController
import com.example.pet_growth_journal.util.SingleLiveEvent
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
) : Fragment() {
    lateinit var binding: CommonAddBottomPopupBinding

        private val addViewModel: AddViewModel by viewModels()

    private val addCategoryAdapter by lazy {
        AddCategoryAdapter(requireContext(), onClickCategory = { category ->
            Log.d("HWO", "addcategory click -> $category")
            addViewModel.onClickCategory(category.id)

        },)
    }

    private val addEmotionAdapter by lazy {
        AddEmotionAdapter(requireContext(), onClickEmotion = { emotion ->
            Log.d("HWO", "add emotion click -> $emotion")
            addViewModel.onClickEmotion(emotion.id)
        })
    }

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
                viewModel = addViewModel
            }

        addViewModel.setCurrentType(CurrentType.PICTURE)

        initObserver(addPopupController)


        binding.rvCategory.adapter = addCategoryAdapter

        val layoutManager = GridLayoutManager(context, 4)
        binding.rvCategory.layoutManager = layoutManager


        binding.rvEmotion.adapter = addEmotionAdapter
        val emotionLayoutManager = GridLayoutManager(context, 4)
        binding.rvEmotion.layoutManager = emotionLayoutManager

        addViewModel.addCategorys.observe(viewLifecycleOwner) {
            Log.d("HWO", "Addcategorys --> $it")
            addCategoryAdapter.submitList(it)
            addCategoryAdapter.notifyDataSetChanged()
            addViewModel.setCurrentType(CurrentType.EMOTION)
        }

        addViewModel.addEmotions.observe(viewLifecycleOwner) {
            addEmotionAdapter.submitList(it)
            addEmotionAdapter.notifyDataSetChanged()
        }

        binding.tvNoPicture.setOnClickListener {
            addViewModel.setCurrentType(CurrentType.CATEGORY)
        }

        return binding.root
    }
    private fun pickFromGallery() {
        val mimeType = arrayOf("image/jpeg", "image/png")
        galleryLauncher.launch(Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
        })
    }

    private fun pickFromCamera() {
        cameraLauncher.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
    }
    val galleryLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val imageBitmap = context?.contentResolver?.loadThumbnail(
                (it.data?.data ?: "") as Uri,
                Size(100, 100),
                CancellationSignal()
            )
            binding.ivBackground.setImageBitmap(imageBitmap)
            binding.ivSelectPicture.visibility = View.GONE
            binding.ivBackground.visibility = View.VISIBLE
            addViewModel.setCurrentType(CurrentType.CATEGORY)
//            binding.ivSelectPicture.setImageBitmap(thumbnail)

        }

    val cameraLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val bundle = it.data?.extras
            val imageBitmap = bundle?.get("data") as Bitmap
            binding.ivBackground.setImageBitmap(imageBitmap)
            binding.ivSelectPicture.visibility = View.GONE
            binding.ivBackground.visibility = View.VISIBLE
            addViewModel.setCurrentType(CurrentType.CATEGORY)
//            binding.ivSelectPicture.setImageBitmap(imageBitmap)
        }


    private fun initObserver(addPopupController: AddPopupController) {


        addPopupController.pictureType.observe(viewLifecycleOwner) { type ->
            when (type) {
                PictureType.GALLERY -> {
                    pickFromGallery()
                }
                PictureType.CAMERA -> {
                    pickFromCamera()
                }
            }

        }
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

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}

class AddPopupDelegate : AddPopupController {
    private val _currentType: MutableLiveData<CurrentType> = MutableLiveData()
    override val currentType: LiveData<CurrentType>
        get() = _currentType

    private val _pictureType= SingleLiveEvent<PictureType>()
    override val pictureType: LiveData<PictureType>
        get() = _pictureType

    override fun setCurrentType(type: CurrentType) {
        _currentType.value = type
    }

    override fun onClickCamera() {
        _pictureType.value = PictureType.CAMERA


    }

    override fun onClickGallery() {
        _pictureType.value = PictureType.GALLERY
    }

}

interface AddPopupController : PopupController {
    fun setCurrentType(type: CurrentType)
    val currentType: LiveData<CurrentType>
    fun onClickGallery()
    fun onClickCamera()

    val pictureType: LiveData<PictureType>

}
