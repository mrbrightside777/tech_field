package com.example.firebaseexample.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.firebaseexample.R
import com.example.firebaseexample.databinding.LoginScreenBinding
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class LoginFrag: Fragment() {
    lateinit var binding: LoginScreenBinding
    lateinit var auth:FirebaseAuth
    lateinit var auth_ui:AuthUI
    val SIGN_IN = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_screen, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        auth_ui = AuthUI.getInstance()
        startActivityForResult(auth_ui
                .createSignInIntentBuilder()
                .setAvailableProviders(mutableListOf(
                        AuthUI.IdpConfig.GoogleBuilder().build(),
                        AuthUI.IdpConfig.EmailBuilder().build())).build(),
                SIGN_IN)



    }

    private fun redirect_to_main_frag() {
        activity?.let {
            it.supportFragmentManager.beginTransaction().replace(R.id.frag_container, MainFrag()).commit()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            SIGN_IN -> {
                if (resultCode == RESULT_OK) {
                    redirect_to_main_frag()
                }
            }
        }
    }
}