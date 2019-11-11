package com.example.medlemskort


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.medlemskort.databinding.FragmentViewCardsBinding

/**
 * A simple [Fragment] subclass.
 */
class ViewCardsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val binding: FragmentViewCardsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_view_cards, container, false
        )

        binding.card1Imageview.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_view_cards_to_fragment_view_card_barcode)
        }

        binding.card2Imageview.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_view_cards_to_fragment_view_card_barcode)
        }

        binding.card3Imageview.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_view_cards_to_fragment_view_card_barcode)
        }

        binding.card4Imageview.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_view_cards_to_fragment_view_card_barcode)
        }

        binding.newCardFloatingActionButton.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_view_cards_to_fragment_create_card_template)
        }



        return binding.root
    }


}
