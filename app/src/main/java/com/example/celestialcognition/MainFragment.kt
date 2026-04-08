package com.example.celestialcognition

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class MainFragment : Fragment(R.layout.fragment_start) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginBtn = view.findViewById<Button>(R.id.loginButton)
        val signUpBtn = view.findViewById<Button>(R.id.signUpButton)

        loginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_loginFragment)
        }

        signUpBtn.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_signUpFragment)
        }
    }
}