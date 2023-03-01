package com.example.pet_growth_journal

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.example.pet_growth_journal.databinding.ActivityMainBinding
import com.example.pet_growth_journal.databinding.ActivityOnboardingBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkFcmToken()
        checkFirestore()
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


//
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_daily_grow, R.id.navigation_add, R.id.navigation_total
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
//
//        setClickListener()


    private fun setOnClickListener() {
//        binding.btnAddRecord.setOnClickListener {
//            findNavController(R.id.nav_host_fragment_activity_main).navigate(
//
//            )
//        }
    }

    private fun checkFcmToken() {
        FirebaseMessaging.getInstance().token.addOnSuccessListener {  fcmToken ->
            Log.d("HWO", "token value -> $fcmToken")
        }
    }

    private fun checkFirestore() {
        val db = Firebase.firestore

        val user = hashMapOf(
            "category" to "산책",
            "emotion" to "좋음",
            "pet" to "건빵",
            "id" to 1111
        )

        db.collection("record")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("HWO", "DocumentSnapshot -> ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.d("HWO", "Error adding document", e)
            }
    }
}