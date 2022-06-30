package com.example.eventmanager.fragments.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.eventmanager.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        view.findViewById<TextView>(R.id.loginLink).setOnClickListener{
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        view.findViewById<Button>(R.id.registerButton).setOnClickListener{
            val email = view.findViewById<TextView>(R.id.textRegisterEmail).text.toString();
            val password = view.findViewById<TextView>(R.id.textRegisterPassword).text.toString();

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener() {task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    findNavController().navigate(R.id.action_registerFragment_to_mapFragment)
                    Toast.makeText(activity, "Registered and logged in Succesfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }


        }

        return view
    }
}