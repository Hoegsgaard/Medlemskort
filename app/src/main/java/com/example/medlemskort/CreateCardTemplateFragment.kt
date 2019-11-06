package com.example.medlemskort


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.medlemskort.databinding.FragmentCreateCardTemplateBinding

/**
 * A simple [Fragment] subclass.
 */
class CreateCardTemplateFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCreateCardTemplateBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_create_card_template, container, false
        )

        binding.button4.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_create_card_template_to_fragment_create_card_input)
        }
        return binding.root
    }


}
