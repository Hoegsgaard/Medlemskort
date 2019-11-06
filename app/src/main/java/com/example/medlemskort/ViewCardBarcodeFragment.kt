package com.example.medlemskort


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.medlemskort.databinding.FragmentViewCardBarcodeBinding

/**
 * A simple [Fragment] subclass.
 */
class ViewCardBarcodeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val binding: FragmentViewCardBarcodeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_view_card_barcode, container, false
        )

        binding.button5.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_view_card_barcode_to_fragment_view_card_notes)
        }
        return binding.root
    }


}
