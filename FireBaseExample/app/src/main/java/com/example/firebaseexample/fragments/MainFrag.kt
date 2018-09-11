package com.example.firebaseexample.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseexample.Constants
import com.example.firebaseexample.Message
import com.example.firebaseexample.R
import com.example.firebaseexample.adapter.MessageRecycler
import com.example.firebaseexample.databinding.ActivityMainBinding
import com.example.firebaseexample.databinding.MainLayoutBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainFrag: Fragment() {
    lateinit var database: FirebaseDatabase
    lateinit var auth: FirebaseAuth
    lateinit var databaseReference: DatabaseReference
    var userName: String = Constants.ANONYMOUS
    lateinit var binding: MainLayoutBinding
    lateinit var valueEventListener: ValueEventListener
    val TAG = "MainActivity"
    val RC_SIGN_IN = 1




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_layout, container, false)
        binding.recycler.adapter = MessageRecycler()
        binding.recycler.layoutManager = LinearLayoutManager(activity)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeFirebaseComponents()
        addTextWatchers()
    }

    private fun initializeFirebaseComponents() {
        FirebaseApp.initializeApp(activity?.applicationContext)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        databaseReference = database.reference.child("messages")


    }

    private fun addTextWatchers() {
        binding.messageET.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.sendButton.isEnabled = s.toString().isNotBlank()
            }

        })

    }

    override fun onResume() {
        super.onResume()
        addReadListener()
    }

    override fun onPause() {
        super.onPause()
        removeReadListeners()
    }

    private fun removeReadListeners() {
        databaseReference.removeEventListener(valueEventListener)
    }


    private fun addReadListener() {
        valueEventListener = object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val adapter = binding.recycler.adapter as MessageRecycler
                dataSnapshot.children.forEach {
                    var message = it.getValue(Message::class.java)
                    message?.let {
                        adapter.add_item(it)
                    }
                }
                adapter.notifyDataSetChanged()

            }

        }

        databaseReference.addValueEventListener(valueEventListener)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            RC_SIGN_IN -> {
                val response = IdpResponse.fromResultIntent(data)
                if (resultCode == Activity.RESULT_OK) {
                    val user = GoogleSignIn.getSignedInAccountFromIntent(data)
                    try {
                        // Google Sign In was successful, authenticate with Firebase
                        val account = user.getResult(ApiException::class.java)
                    } catch (e: ApiException) {
                        // Google Sign In failed, update UI appropriately

                        // ...
                    }

                }
            }
        }
    }

}