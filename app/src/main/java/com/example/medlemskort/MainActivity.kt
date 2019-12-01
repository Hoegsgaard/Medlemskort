package com.example.medlemskort

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.medlemskort.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        }

    //TODO Add some fitting animations when navigating the app
    //TODO Tutorial 4 i AndroidX Tutorial'en viser hvordan man sørger for ens data er persistent. Sørg for data er persistent (Ikke dør hvis app'en dør, hvis man vender skærmen, lukker app'en etc.
    //TODO Lock the orientation to portrait?
    }
