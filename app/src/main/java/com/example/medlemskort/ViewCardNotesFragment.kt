package com.example.medlemskort


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.medlemskort.databinding.FragmentViewCardNotesBinding

/**
 * A simple [Fragment] subclass.
 */
class ViewCardNotesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentViewCardNotesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_view_card_notes, container, false
        )

        binding.button6.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_view_card_notes_to_fragment_edit_notes)
        }
        binding.button7.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_view_card_notes_to_fragment_view_card_barcode)
        }
        return binding.root
    }


}
