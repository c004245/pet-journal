package com.example.pet_growth_journal

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pet_growth_journal.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkFcmToken()
        checkFirestore()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
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