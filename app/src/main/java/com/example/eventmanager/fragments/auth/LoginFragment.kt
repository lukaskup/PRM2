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

class LoginFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        view.findViewById<TextView>(R.id.registerLink).setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        view.findViewById<Button>(R.id.loginButton).setOnClickListener{
            val email = view.findViewById<TextView>(R.id.textLoginEmail).text.toString();
            val password = view.findViewById<TextView>(R.id.textLoginPassword).text.toString();

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(R.id.action_loginFragment_to_mapFragment)
                        Toast.makeText(activity, "Registered and logged in Succesfully!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(activity, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        return view
    }
}