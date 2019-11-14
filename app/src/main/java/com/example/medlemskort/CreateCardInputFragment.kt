package com.example.medlemskort


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        /*
        Create card and navigate to View Barcode Fragment
         */
        binding.scanImageView.setOnClickListener{
            Toast.makeText(requireContext(),"Funktion under udvikling", Toast.LENGTH_LONG).show()
        }
        binding.addCardButton.setOnClickListener { view: View ->
            addCardToDatabase()
            Navigation.findNavController(view)
                .navigate(CreateCardInputFragmentDirections.actionFragmentCreateCardInputToFragmentViewCardBarcode())
        }

        binding.storeNameEdittext.setText(CreateCardInputFragmentArgs.fromBundle(arguments!!).storeName)
        //binding.headerImageview.setImageResource("COLOR / LOGO") //TODO Set this to the correct logo from CreateCardTemplateFragment
        //binding.circleImageview.setImageResource("LOGO") //TODO Set this to the correct logo from CreateCardTemplateFragment

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
