package me.kishankumar.newsfeed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import me.kishankumar.newsfeed.ui.auth.LoginActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser

        val intent: Intent = if(currentUser==null){
            Intent(this,LoginActivity::class.java)
        } else{
            Intent(this,MainActivity::class.java)
        }

        startActivity(intent)
        finish()

    }
}