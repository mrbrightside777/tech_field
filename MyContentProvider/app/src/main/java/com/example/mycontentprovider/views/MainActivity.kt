package com.example.mycontentprovider.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mycontentprovider.R
import com.example.mycontentprovider.content_provider.AnimalsContentProvider
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launch(context = CommonPool) {
            val cursor = contentResolver.query(AnimalsContentProvider.ANIMAL_URI, arrayOf("name"), "name = ?", arrayOf("dog"), null)

        }
    }
}
