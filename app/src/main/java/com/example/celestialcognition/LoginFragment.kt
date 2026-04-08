package com.example.celestialcognition

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        val emailET = view.findViewById<EditText>(R.id.usernameET)
        val passwordET = view.findViewById<EditText>(R.id.passwordET)
        val loginBtn = view.findViewById<Button>(R.id.loginButton)
        val signUpToggle = view.findViewById<Button>(R.id.signUpToggle)


        loginBtn.setOnClickListener {
            val email = emailET.text.toString().trim()
            val password = passwordET.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()

                        findNavController().navigate(R.id.action_loginFragment_to_categoryFragment)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Error: ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

        signUpToggle.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

    override fun onStart() {
        super.onStart()

        if (FirebaseAuth.getInstance().currentUser != null) {
            findNavController().navigate(R.id.action_loginFragment_to_categoryFragment)
        }
    }
}