package me.kishankumar.newsfeed.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import me.kishankumar.newsfeed.MainActivity
import me.kishankumar.newsfeed.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val TAG = "mytest"
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.login.setOnClickListener {

            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            Log.d(TAG, "onCreate: $email $password")

            if(email.isNotEmpty() && password.isNotEmpty()){
                binding.loading.visibility = View.VISIBLE
                signInWithEmailAndPassword(email, password)
            }

        }

        binding.signup.setOnClickListener {
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun signInWithEmailAndPassword(email:String, password:String){

        Log.d(TAG, "signInWithEmailAndPassword: $email $password")

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->

                binding.loading.visibility = View.GONE

                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    //val user = auth.currentUser
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    //updateUI(null)
                }
            }
    }
}