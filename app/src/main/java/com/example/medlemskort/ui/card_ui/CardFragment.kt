package com.example.medlemskort.ui.card_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.medlemskort.R

class CardFragment : Fragment() {

    private lateinit var cardViewModel: CardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cardViewModel =
            ViewModelProviders.of(this).get(CardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_cardoverview, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        cardViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}