package com.example.medlemskort


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.medlemskort.databinding.FragmentCreateCardInputBinding

/**
 * A simple [Fragment] subclass.
 */
class CreateCardInputFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCreateCardInputBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_create_card_input, container, false
        )

        /*
        Create card and navigate to View Barcode Fragment
         */
        binding.addCardButton.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_create_card_input_to_fragment_view_card_barcode)
        }
        return binding.root
    }


}
