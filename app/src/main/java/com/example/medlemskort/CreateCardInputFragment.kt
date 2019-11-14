package com.example.medlemskort


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.medlemskort.databinding.FragmentCreateCardInputBinding
import com.google.firebase.database.FirebaseDatabase



/**
 * A simple [Fragment] subclass.
 */
class CreateCardInputFragment : Fragment() {

    lateinit var binding: FragmentCreateCardInputBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
         binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_create_card_input, container, false
        )

        binding.addCardButton.setOnClickListener { view: View ->
            addCardToDatabase()
            Navigation.findNavController(view)
                .navigate(CreateCardInputFragmentDirections.actionFragmentCreateCardInputToFragmentViewCardBarcode())
        }
        return binding.root
    }

    private fun addCardToDatabase()
    {
        // Write a message to the database
        //TODO First child of the new card should be a unique ID and not the name of the card
        val database = FirebaseDatabase.getInstance()
        val cardRef = database.getReference("cards")
        val newCard = Card(binding.storeNameEdittext.text.toString(), binding.cardNumberEdittext.text.toString().toLong())
        cardRef.child(binding.storeNameEdittext.text.toString()).setValue(newCard)
    }


}
