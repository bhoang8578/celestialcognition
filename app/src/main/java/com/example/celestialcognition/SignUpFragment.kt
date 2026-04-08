package com.example.celestialcognition

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        val nameET = view.findViewById<EditText>(R.id.nameET)
        val emailET = view.findViewById<EditText>(R.id.emailET)
        val passwordET = view.findViewById<EditText>(R.id.passwordET)
        val createBtn = view.findViewById<Button>(R.id.createAccountButton)
        val loginToggle = view.findViewById<Button>(R.id.loginToggle)

        createBtn.setOnClickListener {
            val name = nameET.text.toString().trim()
            val email = emailET.text.toString().trim()
            val password = passwordET.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(requireContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireContext(), "Account created!", Toast.LENGTH_SHORT).show()

                        findNavController().navigate(R.id.action_signUpFragment_to_categoryFragment)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Error: ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

        loginToggle.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }
}